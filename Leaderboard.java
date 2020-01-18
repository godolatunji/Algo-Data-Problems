/**
 * Construct a leadership with the following methods
 * 
 * add(String username, int score)
 * 
 * getPosition(int score)
 * 
 * getScore(int username)
 * 
 * 
 */

import java.util.*;


class User {
     public String username;
     public int score;

     User(String username, int score) {
         this.username = username;
         this.score = score;
     }

     public String getUsername() {
         return this.username;
     }

     public int getScore() {
         return this.score;
     }
 }

public class Leaderboard {
    private ArrayList<User> list;

    Leaderboard() {
        list = new ArrayList<>();
    }

    public int findByUsername(String username) {
        int index = -1;
        int flag = -1;

        for (User user: list) {
            index++;
            String userName = user.getUsername();

            if (username.equals(userName)) {
                flag = index;
                break;
            }
        }

        return flag; 
    }

    public int findByScore(int score) {
        int index = -1;
        int flag = -1;

        for (User user: list) {
            index++;
            int userScore = user.getScore();

            if (score == userScore) {
                flag = index;
                break;
            }
        }

        return flag; 
    }

    public void add(String username, int score) {
        int flag = this.findByUsername(username);

        if (flag == -1) {
            User user = new User(username, score);
            list.add(user);
        } else {
            User user = (User) list.get(flag);
            user.score = score;
            list.set(flag, user);
        }

        Collections.sort(list, (a, b) -> Integer.compare(b.getScore(), a.getScore()));
    }

    public int getPosition(int score) {
        int position = this.findByScore(score);

        return position;
    }

    public int getScore(String username) {
        int position = this.findByUsername(username);

        if (position == -1) {
            throw new NotFoundException("username '" + username + "' not found");
        }

        User user = (User) list.get(position);

        return user.getScore();
    }

    public void print() {
        System.out.println("-----begin");
        for (User user: list) {
            System.out.printf("[%s, %s] \n", user.getUsername(), user.getScore());
        }
        System.out.println("-----end");
    }


    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.add("user1", 10);
        leaderboard.add("user3", 20);
        leaderboard.add("user5", 3);
        leaderboard.add("user9", 11);
        leaderboard.add("user2", 9);
        leaderboard.add("user8", 17);

        leaderboard.print();

        leaderboard.add("user2", 13);

        leaderboard.print();

        System.out.printf("position of user2 with score 13 is %s\n", leaderboard.getPosition(13));

        System.out.printf("score of user5 is %s\n", leaderboard.getScore("user5"));

        // should throw error
        System.out.printf("score of unknown is %s\n", leaderboard.getScore("unknown"));
    }
}

class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}