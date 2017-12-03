package befaster.solutions;

public class Hello {
    public static String hello(String friendName) {
        return "Hello, " + (friendNameIsGiven(friendName) ? friendName : "World") + "!";
    }

    private static boolean friendNameIsGiven(String friendName) {
        return friendName != null && !friendName.isEmpty();
    }
}
