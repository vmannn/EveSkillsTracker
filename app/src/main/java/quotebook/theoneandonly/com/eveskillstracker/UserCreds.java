package quotebook.theoneandonly.com.eveskillstracker;

//API mapping JSON for name and ID of character. Character
//ID is used later for API calls

class UserCreds {

    private String CharacterName;
    private String CharacterID;

    public String getChar_id() {
        return CharacterID;
    }

    public String getChar_name() {
        return CharacterName;
    }
}
