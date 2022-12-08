package base;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        int n = 5;
        int m = 6;
        Map<String, Integer> translate = new HashMap<>();

        ControllerCvs contr = new ControllerCvs();
        contr.initFile(4 , m*n );

        GUI board = new GUI();


        ExplorationPolicy explorationPolicy = new ExplorationPolicy();
        Sarsa sarsa = new Sarsa(m * n, 4, explorationPolicy, false);
        contr.read();
        sarsa.setQvalues(contr.read());
        while(true) {
            int x = 0, buffX = 0;
            int y = 0, buffY = 0;
            int previousAction = 1;
            boolean[][] playMap = new boolean[n][m];
            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    translate.put(Integer.toString(i) + Integer.toString(j), k);
                    k++;
                    playMap[i][j] = false;
                }
            }
            playMap[x][y] = true;

            while (true) {
                String key = Integer.toString(x) + Integer.toString(y);
                int action = sarsa.GetAction(translate.get(key));
                System.out.println(action);
//                action = 2;
                buffX = x;
                buffY = y;
                if (action == 0) {
                    buffX = x + 1;
                } else if (action == 1) {
                    buffX = x - 1;
                } else if (action == 2) {
                    buffY = y + 1;
                } else if (action == 3) {
                    buffY = y - 1;
                }
                String newKey = Integer.toString(buffX) + Integer.toString(buffY);
                if (buffX == n - 1 && buffY == m - 1) {
                    sarsa.UpdateState(translate.get(key), previousAction,
                            100, translate.get(newKey), action);
//                    return;
                    break;
                } else if (buffX < 0 || buffX >= n || buffY < 0 || buffY >= m) {
                    sarsa.UpdateState(translate.get(key), previousAction,
                            -40, 0, action);
                    break;
                } else {
                    sarsa.UpdateState(translate.get(key), previousAction,
                            -10, translate.get(newKey), action);
                }
                playMap[x][y] = false;
                playMap[buffX][buffY] = true;
                x = buffX;
                y = buffY;
                previousAction = action;
                float[] stateValue = sarsa.getQvaluesSum();
                float array2d[][] = new float[n][m];
                float sum = 0;
                for(int i = 0; i < n*m; i++){
                    sum += stateValue[i];
                }

                for(int i=0; i<n; i++)
                    for(int j=0;j<m; j++)
                        array2d[i][j] = stateValue[(j*n) + i];
                board.setBoard(array2d, sum);
                board.setAgentPosition(x, y);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if(playMap[i][j])
                            System.out.print(1);
                        else
                            System.out.print(0);
                    }
                    System.out.println();
                }
            }
            contr.updateFile(sarsa.getQvalues());
            System.out.println("-----------------");
        }
    }

}
