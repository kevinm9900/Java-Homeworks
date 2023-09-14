package tournament;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BracketImpl_Moran<P> extends BracketAbstract<P>
{
	public BracketImpl_Moran(List<P> participantMatchups)
	{
		super(participantMatchups);
	}
	

	public int getMaxLevel() {
        int numNodes = getNumNodes();
        int maxLevel = log_2(numNodes + OFFSET_BY_ONE) - OFFSET_BY_ONE;
        return maxLevel;
    }

    public Set<Set<P>> getGroupings(int level) {
        assert 0 <= level : "Invalid send! : <" + level + "> " + "You cannot send a negative level!";
        assert level <= getMaxLevel() : "Invalid send! : <" + level + "> " +
                                        "You cannot send a level greater than the possible amount of levels!";

        Set<Set<P>> rv = new HashSet<>();

        int inverseLevel = getInverseLevel(level);
        int rvSize = (int) Math.pow(2, inverseLevel);
        int groupingSize = (int) Math.pow(2, level);
        int itr = getStartIndexOfLevelN(0);

        for (int group = 0; group < rvSize; group++) {
            int start = itr + group * groupingSize;
            int end = start + groupingSize;
            Set<P> grouping = new HashSet<>(predictions.subList(start, end));
            rv.add(grouping);
        }
        return rv;
    }

    public Set<P> getViableParticipants(Set<P> grouping) {
        assert grouping != NO_PARTICIPANT : "Invalid send! : <null> You cannot send a null value!";
        assert !grouping.contains(NO_PARTICIPANT) :
                "Invalid send! : <" + grouping + "> " + "You cannot send a grouping that contains a null!";
        assert predictions.containsAll(grouping) : "Invalid send! : <" + grouping + "> " +
                                                   "You cannot send a grouping with participant(s) that aren't in the" +
                                                   " bracket!";
        assert getGroupings(log_2(grouping.size())).contains(grouping) : " Invalid send! : <" + grouping + "> " +
                                                                         "You cannot send a grouping that isn't a " +
                                                                         "valid grouping in this bracket!";

        Set<P> rv = new HashSet<>(grouping);

        for (P participant : grouping) {
            boolean eliminated = false;
            int highestGroupLevel = log_2(grouping.size());
            int level = 0;
            int index = getParentIndex(getIndexOfParticipantOnLevelN(participant, 0));
            while (level < highestGroupLevel && !eliminated) {
                P curNode = predictions.get(index);
                eliminated = curNode != participant && curNode != NO_PARTICIPANT;
                if (eliminated) {
                    rv.remove(participant);
                }
                index = getParentIndex(index);
                level++;
            }
        }

        return rv;
    }

    public void setWinCount(P participant, int winCount) {
        assert participant != NO_PARTICIPANT : "Invalid send! : <null> You cannot send a null value!";
        assert predictions.contains(participant) : "Invalid send! : <" + participant + "> " +
                                                   "You cannot ask about a participant who isn't in the bracket!";
        assert 0 <= winCount : "Invalid send! : <" + winCount + "> " + "You cannot have a negative winCount!";
        assert winCount <= getMaxLevel() :
                "Invalid send! : <" + winCount + "> " + "You cannot have more wins than possible!";

        int parentIndex = getIndexOfParticipantOnLevelN(participant, 0);
        int levelsLeftToCover = getMaxLevel() - winCount;
        P replacedParticipant = NO_PARTICIPANT;

        for (int win = 0; win < winCount; win++) {
            parentIndex = getParentIndex(parentIndex);
            replacedParticipant = predictions.get(parentIndex);
            predictions.set(parentIndex, participant);
        }

        int level = 0;
        boolean wasReplaced = true;
        boolean isNull = false;
        while (level < levelsLeftToCover && wasReplaced && !isNull) {
            parentIndex = getParentIndex(parentIndex);
            P parent = predictions.get(parentIndex);
            wasReplaced = parent == replacedParticipant;
            isNull = parent == NO_PARTICIPANT;
            if (wasReplaced && !isNull) {
                predictions.set(parentIndex, NO_PARTICIPANT);
                level++;
            }
        }
    }

    private final P NO_PARTICIPANT = null;
    private final int OFFSET_BY_ONE = 1;

    private int getNumNodes() {
        return predictions.size();
    }

    private int log_2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    private int getInverseLevel(int level) {
        assert 0 <= level;
        assert level <= getMaxLevel();

        return getMaxLevel() - level;
    }

    private int getStartIndexOfLevelN(int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int inverseLevel = getInverseLevel(n);
        return (int) Math.pow(2, inverseLevel) - OFFSET_BY_ONE;
    }

    private int getEndIndexOfLevelN(int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int startIndex = getStartIndexOfLevelN(n);
        return 2 * startIndex;
    }

    private int getIndexOfParticipantOnLevelN(P participant, int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int itr = getStartIndexOfLevelN(n);
        int end = getEndIndexOfLevelN(n);
        int rv = itr;
        boolean found = false;
        while (itr < end && !found) {
            found = predictions.get(itr) == participant;
            if (found) {
                rv = itr;
            }
            itr++;
        }
        return rv;
    }

    private int getParentIndex(int childIndex) {
        assert childIndex >= 0;
        assert childIndex < getNumNodes();

        int numerator = childIndex % 2 == 0 ? (childIndex - 2) : (childIndex - 1);
        return numerator / 2;
    }

    private final String WHITE_SPACE_PADDING = " ";
    private final String DASH_PADDING = "-";
    private final String SPLAT = "*";

    private String levelBasedToString() {
        StringBuilder sb = new StringBuilder();

        int maxLevel = getMaxLevel();
        for (int i = maxLevel; i >= 0; i--) {
            StringBuilder inverseLevel_i = new StringBuilder();
            StringBuilder leftSidePadding = new StringBuilder();
            StringBuilder partPaddingSpace = new StringBuilder();
            StringBuilder partPaddingMatch = new StringBuilder();


            for (int ws = 0; ws < (int) Math.pow(2, i); ws++) {
                leftSidePadding.append(WHITE_SPACE_PADDING);
            }
            for (int ws = 0; ws < (int) Math.pow(2, i + OFFSET_BY_ONE) - OFFSET_BY_ONE; ws++) {
                partPaddingSpace.append(WHITE_SPACE_PADDING);
            }
            for (int ws = 0; ws < (int) Math.pow(2, i + OFFSET_BY_ONE) - OFFSET_BY_ONE; ws++) {
                partPaddingMatch.append(DASH_PADDING);
            }
            int start = getStartIndexOfLevelN(i);
            int end = getEndIndexOfLevelN(i);

            inverseLevel_i.append(leftSidePadding);
            for (int j = start; j <= end; j++) {
                P participant = predictions.get(j);
                if (participant == NO_PARTICIPANT) {
                    inverseLevel_i.append(SPLAT);
                } else {
                    inverseLevel_i.append(participant);
                }
                if (j != end) {
                    if (j % 2 == 0) {
                        inverseLevel_i.append(partPaddingSpace.toString());
                    } else {
                        inverseLevel_i.append(partPaddingMatch.toString());
                    }
                }
            }
            sb.append(inverseLevel_i + "\n");


        }
        return "\n" + sb.toString();
    }

    public String toString() {
        return levelBasedToString();
        }
    
}