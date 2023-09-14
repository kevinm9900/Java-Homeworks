package tictactoe;

public class TicTacToeBoardImpl_Moran implements TicTacToeBoard
{
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	protected int[] movesArray;

	public TicTacToeBoardImpl_Moran()
	{
		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for(int i = 0; i < CELL_COUNT; i++)
		{
			movesArray[i] = NO_MOVE;
		}
	}
	public String toString()
	{
	    StringBuffer sb = new StringBuffer();
	    sb.append(stringFromPosition(0,0) + "|" + stringFromPosition(0,1) + "|" + stringFromPosition(0,2));
	    sb.append("\n\n");
	    sb.append(stringFromPosition(1,0) + "|" + stringFromPosition(1,1) + "|" + stringFromPosition(1,2));
	    sb.append("\n\n");
	    sb.append(stringFromPosition(2,0) + "|" + stringFromPosition(2,1) + "|" + stringFromPosition(2,2));
	    return sb.toString();

	}

    public String stringFromPosition(int row, int col)
    {
        String retStr = "";
        if(getMark(row, col) == Mark.X)
        {
            retStr = "X";
        }
        else if(getMark(row, col) == Mark.O)
        {
            retStr = "O";
        }
        else if(getMark(row, col) == null)
        {
            retStr = " ";
        }
        return retStr;
    }

//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
//part of post: rv== null <==> the (row, column) spot on the
//board is empty
	public Mark getMark(int row, int column)
	{
		assert (0 <= row && row < ROW_COUNT);
		assert (0 <= column && column < COLUMN_COUNT);
		Mark retVal = null;

		if(row == 0)
		{
			if(column == 0)
			{
				retVal = xOrO(0);
			}
			else if(column == 1)
			{
				retVal = xOrO(1);
			}
			else if(column == 2)
			{
				retVal = xOrO(2);
			}
		}
		else if(row == 1)
		{
			if(column == 0)
			{
				retVal = xOrO(3);
			}
			else if(column == 1)
			{
				retVal = xOrO(4);
			}
			else if(column == 2)
			{
				retVal = xOrO(5);
			}
		}
		else if(row == 2)
		{
			if(column == 0)
			{
				retVal = xOrO(6);
			}
			else if(column == 1)
			{
				retVal = xOrO(7);
			}
			else if(column == 2)
			{
				retVal = xOrO(8);
			}
		}
		return retVal;

	}

	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of pre: getMark(row, column) == null
	//part of pre: !isGameOver()

	public void setMark(int row, int column)
	{
		assert (0 <= row && row < ROW_COUNT);
		assert (0 <= column && column < COLUMN_COUNT);
		assert getMark(row, column) == null;
		assert isGameOver() == false;

		int position = -1;
		int indexOfNegativeOne = 0;

		for(int index = 0; index < movesArray.length; index++)
		{
			if(movesArray[index] == NO_MOVE)
			{
				indexOfNegativeOne = index;
				break;
			}
		}

		if(row == 0)
		{
			if(column == 0)
			{
				position = 0;
			}
			else if(column == 1)
			{
				position = 1;
			}
			else if(column == 2)
			{
				position = 2;
			}
		}
		else if(row == 1)
		{
			if(column == 0)
			{
				position = 3;
			}
			else if(column == 1)
			{
				position = 4;
			}
			else if(column == 2)
			{
				position = 5;
			}
		}
		else if(row == 2)
		{
			if(column == 0)
			{
				position = 6;
			}
			else if(column == 1)
			{
				position = 7;
			}
			else if(column == 2)
			{
				position = 8;
			}
		}
		movesArray[indexOfNegativeOne] = position;

	}
//part of post: rv== null <==> it is neither player's turn (i.e. //game is over)
//part of post: “number of Marks on board is even”èrv == Mark.X
//part of post: “number of Markson board is odd” èrv == Mark.Opublic
	public Mark getTurn()
	{
		Mark retVal = null;
		int moveCounter = 0;
		for(int index = 0; index < movesArray.length; index++)
		{
			if(movesArray[index] != NO_MOVE)
			{
				moveCounter++;
			}
		}
		if(isGameOver()== true)
		{
			return null;
		}
		else if(moveCounter == 9)
		{
			return retVal;
		}
		else if(moveCounter % 2 == 0)
		{
			retVal = Mark.X;
		}
		else if(moveCounter % 2 == 1)
		{
			retVal = Mark.O;
		}
		return retVal;
	}

	public boolean isGameOver()
	{
		boolean retVal = false;

		if(movesArray[8] != NO_MOVE)
		{
			retVal = true;
		}

		else if(getMark(0,0) == getMark(0,1) && getMark(0,1) == getMark(0,2) && getMark(0,0) != null)
		{
			retVal = true;
		}
		else if(getMark(1,0) == getMark(1,1) && getMark(1,1) == getMark(1,2)&& getMark(1,0) != null)
		{
			retVal = true;
		}
		else if(getMark(2,0) == getMark(2,1) && getMark(2,1) == getMark(2,2)&& getMark(2,0) != null)
		{
			retVal = true;
		}
		else if(getMark(0,0) == getMark(1,0) && getMark(1,0) == getMark(2,0) && getMark(0,0) != null)
		{
			retVal = true;
		}
		else if(getMark(0,1) == getMark(1,1) && getMark(1,1) == getMark(2,1) && getMark(0,1) != null)
		{
			retVal = true;
		}
		else if(getMark(0,2) == getMark(1,2) && getMark(1,2) == getMark(2,2) && getMark(0,2) != null)
		{
			retVal = true;
		}
		else if(getMark(0,0) == getMark(1,1) && getMark(1,1) == getMark(2,2)&& getMark(0,0) != null)
		{
			retVal = true;
		}
		else if(getMark(0,2) == getMark(1,1) && getMark(1,1) == getMark(2,0) && getMark(0,2) != null)
		{
			retVal = true;
		}

		return retVal;

	}

	public Mark getWinner()
	{
		assert isGameOver() == true;
		Mark winner = null;

		if(getMark(0,0) == getMark(0,1) && getMark(0,1) == getMark(0,2))
		{
			winner = getMark(0,0);
		}
		else if(getMark(1,0) == getMark(1,1) && getMark(1,1) == getMark(1,2))
		{
			winner = getMark(1,0);
		}
		else if(getMark(2,0) == getMark(2,1) && getMark(2,1) == getMark(2,2))
		{
			winner = getMark(2,0);
		}
		else if(getMark(0,0) == getMark(1,0) && getMark(1,0) == getMark(2,0))
		{
			winner = getMark(0,0);
		}
		else if(getMark(0,1) == getMark(1,1) && getMark(1,1) == getMark(2,1))
		{
			winner = getMark(0,1);
		}
		else if(getMark(0,2) == getMark(1,2) && getMark(1,2) == getMark(2,2))
		{
			winner = getMark(0,2);
		}
		else if(getMark(0,0) == getMark(1,1) && getMark(1,1) == getMark(2,2))
		{
			winner = getMark(0,0);
		}
		else if(getMark(0,2) == getMark(1,1) && getMark(1,1) == getMark(2,0))
		{
			winner = getMark(0,2);
		}
		return winner;

	}
	public Mark xOrO(int specificSquare)
	{
		Mark retVal = null;

		int position = -1;

		for(int index = 0; index < movesArray.length; index++)
		{
			if(movesArray[index] == specificSquare)
			{
				position = index;
			}
		}

		if(position == -1)
		{
			return retVal;
		}
		else if(position % 2 == 0)
		{
			retVal = Mark.X;
		}
		else if(position % 2 == 1)
		{
			retVal = Mark.O;
		}

		return retVal;
	}


}
