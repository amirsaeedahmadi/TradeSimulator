package ir.mas.tradesim.enums;

public enum Regexes {
    //short commands help
    //spell: sp, monster: mn, hand: hd, field: fd, selected: sl
    //username: un, password: pw, nickname: nn, current: cur, deck-name: dn, rounds: rou, second-player: sec-p
    //attack: att, defense: def


    SHOP_SHOW_ALL("shop show --all"),
    SHOW_CARD("^card show ([\\w\\s-]+)"),
    SELECT("^select (?=.*(--(spell|monster|hand|sp|mn|hd) (\\d+))).*$"),
    SELECT_SPELL("^select (?=.*(--(spell|sp) (\\d+))).*$"),   //no usage found
    DESELECT("select -d"),
    SELECT_CARD_FROM_BOARD("^select (?=.*(--(monster|spell|sp|mn) (\\d+))).*$"),  //no usage found
    SELECT_FIELD("^select (?=.*(--(field|fd))).*$"),
    SELECT_HAND("select --(?=hand|hd) \\d+"),   //no usage found
    SELECT_MONSTER("^select (?=.*(--(monster|mn) (\\d+))).*$"),  //no usage found
    SHORT_SELECT_AREA(".*--(mn|hd|sp) (\\d+).*"),
    SELECT_AREA(".*--(monster|hand|spell) (\\d+).*"),
    SELECT_POSITION(".*--(?:monster|hand|spell|sp|mn|hd) (\\d+).*"),
    SHOW_SELECTED_CARD("card show --(selected|sl)"),
    SHOW_GRAVEYARD("show graveyard"),
    SUMMON("summon"),
    FLIP_SUMMON("flip-summon"),
    RITUAL_SUMMON("ritual-summon"),
    SPECIAL_SUMMON("special-summon"),
    SET("set"),
    CANCEL("cancel"),
    NEXT_PHASE(">"),
    SET_POSITION("(?:set --(position) (attack|defense))|(?:set --(pos) (att|def))"),
    ATTACK_TO("attack (\\d)"),
    ACTIVATE_EFFECT("activate effect"),
    IMPORT("import card ([\\w\\s-]+)"),
    EXPORT("export card ([\\w\\s-]+)"),

    MENU_EXIT("menu exit"),
    MENU_CURRENT("menu show-current"),
    MENU_ENTER("menu enter (.*)"),
    CREATE_USER("(?:^user create (?=.*(--(username|un) (\\w+)))(?=.*(--(password|pw) (\\w+)))(?=.*(--(nickname|nn) (\\w+))).*$)"),
    LOGIN_USER("(?:^user login (?=.*(--(username|un) (\\w+)))(?=.*(--(password|pw) (\\w+))).*$)"),
    CHANGE_PROFILE_NICKNAME("^profile change (?=.*(--(nickname|nn) (\\w+))).*$"),
    CHANGE_PROFILE_USERNAME("^profile change (?=.*(--(username|un) (\\w+))).*$"),
    CHANGE_PROFILE_PASSWORD("(?:^profile change (?=.*(--password|--pw))(?=.*(--(current|cur) (\\w+)))(?=.*(--(new) (\\w+))).*$)"),
    CREATE_DECK("deck create (\\w+)"),
    DELETE_DECK("deck delete (\\w+)"),
    ACTIVATE_DECK("deck set-activate (.*)"),
    ADD_CARD("^deck add-card (?=.*(--(card) ([\\w\\- ]+)))(?=.*(--(deck) ([\\w\\-]+))).*$"),
    REMOVE_CARD("^deck rm-card (?=.*(--(card) ([\\w\\-]+)))(?=.*(--(deck) ([\\w\\-]+))).*$"),
    SHOW_ALL_DECKS("deck show --all"),
    SHOW_DECK("^deck show (?=.*(--(deck-name|dn) ([\\w\\-]+))).*$"),
    SHOW_CARDS("deck show --cards"),
    OPTION("--(\\w+)\\b(\\s+--([\\w\\-]+)|$)"),
    DATA("--([\\w-]+) ([\\w ]+)"),
    LOGOUT("user logout"),
    SHOW_SCOREBOARD("scoreboard show"),
    SHOP_BUY("shop buy ([\\w\\s-]+)"),
    START_DUEL("(?:^duel (?=.*(--(second-player|sec-p) ([\\w\\-]+)))(?=.*(--(rounds|rou) (\\d+))).*$)"),
    START_DUEL_AI("(?:^duel (?=.*(--(ai)))(?=.*(--(rounds|rou) (\\d+))).*$)"),
    ATTACK_DIRECT("attack direct"),
    EXTRACT_SHORT_DATA(".*-- ([\\w ]+).*"),
    //cheat
    WIN_GAME("win game"),
    INCREASE_MONEY("increase --money ([\\d]+)"),
    INCREASE_LIFE_POINT("increase --LP ([\\d]+)"),
    SELECT_FORCE("select --hand ([\\w\\s-]+) --force"),
    SURRENDER("surrender");


    public final String label;

    Regexes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

