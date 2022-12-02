package Project;

public class Rules {
    public static int getInvCount(int arr[], int nCell)
    {
        int inv_count = 0;
        for (int i = 0; i < nCell * nCell - 1; i++)
        {
            for (int j = i + 1; j < nCell * nCell; j++)
            {
                if (arr[j] != -1 && arr[i] != -1) {
                    // count pairs(arr[i], arr[j]) such that
                      // i < j but arr[i] > arr[j]
                    if (i < j && arr[i] > arr[j]) {
                        inv_count++;
                    }
                }
            }
        }
        return inv_count;
    }

    public static int findXPosition(int board[][], int nCell) {
        for (int i = nCell - 1; i >= 0; i--) {
            for (int j = nCell - 1; j >= 0; j--) {
                if (board[i][j] == -1) {
                    return nCell - i;
                }
            }
        }
        return '\0';
    }

    public static boolean isSolvable(int board[][], int arr[], int nCell) {
        int invCount = getInvCount(arr, nCell);

        if (nCell % 2 == 1) // grid is odd
            return !(invCount % 2 == 1);
    
        else     // grid is even
        {
            int pos = findXPosition(board, nCell);
            if (pos % 2 == 1)
                return !(invCount % 2 == 1);
            else
                return invCount % 2 == 1;
        }
    }
}
