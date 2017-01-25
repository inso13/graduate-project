package voteforlunch;

import voteforlunch.model.BaseEntity;
import voteforlunch.util.MealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class AuthorizedUser {
    public static int id = BaseEntity.START_SEQ;
    public static boolean isAdmin=false;

    public static boolean isIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        AuthorizedUser.isAdmin = isAdmin;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
