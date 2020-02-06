package code.vipul.aoc2018;

final class Inputs {

    // Part 1 is 264384, Part 2 is 67022
    static final String DAY15_2 =
            "################################\n" +
                    "###########################..###\n" +
                    "##########################...###\n" +
                    "#########################..#####\n" +
                    "####...##################.######\n" +
                    "#####..################...#.####\n" +
                    "#..G...G#########.####G.....####\n" +
                    "#.......########.....G.......###\n" +
                    "#.....G....###G....#....E.....##\n" +
                    "####...##......##.............##\n" +
                    "####G...#.G...###.G...........##\n" +
                    "####G.......................####\n" +
                    "####.........G#####.........####\n" +
                    "####...GG#...#######.......#####\n" +
                    "###.........#########G....######\n" +
                    "###.G.......#########G...#######\n" +
                    "###.G.......#########......#####\n" +
                    "####.....G..#########....E..####\n" +
                    "#####.......#########..E....####\n" +
                    "######...##G.#######........####\n" +
                    "######.#.#.G..#####.....##..####\n" +
                    "########....E...........##..####\n" +
                    "########....E#######........####\n" +
                    "########......######E....##..E.#\n" +
                    "########......#####.....#......#\n" +
                    "########.....######............#\n" +
                    "##################...#.E...E...#\n" +
                    "##################.............#\n" +
                    "###################.......E#####\n" +
                    "####################....#...####\n" +
                    "####################.###########\n" +
                    "################################";

    static final String DAY15 =
            "################################\n" +
            "#G..#####G.#####################\n" +
            "##.#####...#####################\n" +
            "##.#######..####################\n" +
            "#...#####.#.#.G...#.##...###...#\n" +
            "##.######....#...G..#...####..##\n" +
            "##....#....G.........E..####.###\n" +
            "#####..#...G........G...##....##\n" +
            "######.....G............#.....##\n" +
            "######....G.............#....###\n" +
            "#####..##.......E..##.#......###\n" +
            "########.##...........##.....###\n" +
            "####G.G.......#####..E###...####\n" +
            "##.......G...#######..#####..###\n" +
            "#........#..#########.###...####\n" +
            "#.G..GG.###.#########.##...#####\n" +
            "#...........#########......#####\n" +
            "##..........#########..#.#######\n" +
            "###G.G......#########....#######\n" +
            "##...#.......#######.G...#######\n" +
            "##.......G....#####.E...#.######\n" +
            "###......E..G.E......E.....#####\n" +
            "##.#................E.#...######\n" +
            "#....#...................#######\n" +
            "#....#E........E.##.#....#######\n" +
            "#......###.#..#..##.#....#..####\n" +
            "#...########..#..####....#..####\n" +
            "#...########.#########......####\n" +
            "#...########.###################\n" +
            "############.###################\n" +
            "#########....###################\n" +
            "################################";

    static final String DAY13 =
                            "                                         /----------------------------------------------------------------------------------------------------------\\ \n" +
                            "                                         |                                                 /---------------------\\           /-----------------\\    | \n" +
                            "          /-------------------------\\    |                                        /--------+-----------\\         |           | /----------\\    |    | \n" +
                            "          |              /----------+----+----------------------------------------+--------+-----------+-------\\ |           | |          |    |    | \n" +
                            "          | /------------+\\         |    |              /-------------------------+--------+-\\         |       | |           | |          |    |    | \n" +
                            "          | |            ||         |    |         /----+-------------------------+--------+-+---------+-------+-+-\\         | |          |    |    | \n" +
                            "          | |         /--++--------\\|   /+---------+----+-------------------------+--------+\\|         |    /--+-+-+---------+-+----------+-\\  |    | \n" +
                            "          | |         |  ||      /-++---++---------+----+-------------------------+--------+++---------+----+--+-+-+---------+-+----\\     | |  |    | \n" +
                            "          | |         |  ||/-----+-++---++---------+----+--\\   /------------------+--------+++---------+----+--+-+-+---------+-+----+-----+-+--+\\   | \n" +
                            "/---------+-+---------+--+++-----+-++---++---------+----+--+---+-\\                |        |||         |    |  | | |         | |    |     | |  ||   | \n" +
                            "|         | |         |  |||     | ||   ||         |    |  |   | |  /---------\\   |        |||         |    |  | | |         | |    |     | |  ||   | \n" +
                            "|         | |         |  |||    /+-++---++---------+----+--+---+-+--+---------+---+--------+++---------+----+--+-+\\|         | |    |     | |  ||   | \n" +
                            "|         | |         |  |||    || ||   ||         |   /+--+---+-+--+---------+---+--------+++------\\  |    |  | |||         | \\----+-----/ |  ||   | \n" +
                            "|      /--+-+---------+--+++----++-++---++---------+---++--+---+-+--+-------\\ |   |        |||      |  |    |  | |||         |      |       |  ||   | \n" +
                            "|  /---+--+-+---------+--+++----++-++---++->\\      |   ||  |   | |  |       | |   |        |||      |  |    |  | |||         |      |       |  ||   | \n" +
                            "|  |   |  | |         |  |||    || ||   ||  |  /---+---++--+---+-+--+-------+-+---+--------+++------+--+----+--+-+++---------+----\\ |       |  ||   | \n" +
                            "|  |   |  | |         |  |||    || ||   ||  |  v   |/--++--+-\\ | |  |       | |   |        |||  /---+--+----+--+-+++--------\\|    | |       |  ||   | \n" +
                            "|  |   |  | |         |/-+++----++-++---++--+--+---++--++--+-+-+-+--+-------+\\|   |        |||  |   |  | /--+--+-+++--------++----+-+-------+--++--\\| \n" +
                            "|  |   |  | |     /---++-+++----++-++---++--+--+---++--++--+-+-+-+--+--\\    |||   |        |||  |   |  | |  |  | |||        ||    | |       |  ||  || \n" +
                            "|  |   |  | |     |   || |||    || ||   ||  |  |   ||  ||  | | | |  |  |    |||   |        |||  | /-+--+-+--+--+-+++--------++----+-+---\\   |  ||  || \n" +
                            "|  |   |  | |     |   || |||    || ||   ||  |  |   ||  ||/-+-+-+-+--+--+----+++---+--------+++--+-+-+--+-+--+--+-+++--------++-\\  | |   |   |  ||  || \n" +
                            "|  |   |  | |  /--+---++-+++----++-++---++--+--+---++--+++-+-+-+-+\\ |  | /--+++---+--------+++--+-+-+>-+-+--+--+-+++--------++-+--+-+---+---+--++\\ || \n" +
                            "|  |   |  | |  | /+---++-+++----++\\||   \\+--+--+---++--+++-+-+-+-++-+--+-+--+++---+--------+/|  | | |  | |  |/-+-+++--------++-+--+-+---+-\\ |  ||| || \n" +
                            "|  |   |  | |  | ||   || |||    |||||    |  |  |   ||  ||| | | | || |  | |  |||   | /------+-+--+-+-+--+-+--++-+-+++--------++-+\\ | |   | | |  ||| || \n" +
                            "|  |   |  | |  | ||   \\+-+++----+++/|    \\--+--+---++--+++-+-+-+-++-+--+-+--+++---+-+------+-+--+-+-+--+-+--++-+-+++--------++-++-+-+-<-+-+-+--+++-+/ \n" +
                            "|  \\---+--+-+--+-++----+-+++----+++-+-------/  |  /++--+++-+-+-+-++-+--+\\|  |||   | |      \\-+--+-+-+--+-+--++-+-/||        || || | |   | | |  ||| |  \n" +
                            "|      |  | |  | ||    | |||    ||| |          |  |||  ||| | | | || |  |||  |||   | |        | /+-+-+--+-+--++-+--++--------++-++-+-+--\\| | |  ||| |  \n" +
                            "|      |  | |  | ||    | |||    ||| |          |  |||  ||| | | | || |  |||  |||   | |        | || | |  | |  || |  ||        || || | |  || | |  ||| |  \n" +
                            "|      |  | |  | ||    | |||    ||| |          |  |||  ||| | | | || |  |||  |||   | |  /-----+-++-+-+--+-+--++-+--++------\\ || || | |  || | |  ||| |  \n" +
                            "|      |  | |  |/++----+-+++----+++-+----------+--+++--+++-+-+-+-++-+--+++--+++---+-+--+-----+-++-+-+--+-+--++-+-\\||      | || || | |  || | |  ||| |  \n" +
                            "|      |/-+-+--++++----+-+++----+++-+----------+--+++--+++-+-+-+\\|| |  |||  |||   | |  |     | ||/+-+--+-+--++-+-+++-----\\| || ||/+-+--++-+-+--+++\\|  \n" +
                            "|/-----++-+-+--++++----+-+++----+++-+----------+--+++\\ ||| | | |||| |  |||  |||   | |  | /---+-++++-+--+-+--++-+-+++-----++-++-++++-+--++-+-+--+++++\\ \n" +
                            "||     || | |  ||||/---+-+++----+++-+----------+\\ |||| ||| | | |||| |  |||  |||   \\-+--+-+---+-++++-+--/ |  || | |||     || || |||| |  || | |  |||||| \n" +
                            "||     || | |  |||||   | |||    |||/+----------++-++++-+++-+-+-++++-+--+++--+++-----+--+-+---+-++++-+----+--++-+-+++-----++-++-++++-+--++-+\\|  |||||| \n" +
                            "||     || | |  |||||   | |||    |||||          || ^||| ||| | | |||| |  |||  |||     |  | |   | |||| |    |  || | |||     || || |||| |  || |||  |||||| \n" +
                            "||     || | |  |||||   | |||   /+++++----------++-++++-+++-+-+-++++-+--+++--+++-----+--+-+---+-++++-+----+--++-+-+++-----++-++-++++\\|  || |||  |||||| \n" +
                            "||     || | |/-+++++---+-+++-\\ ||||||          || |||| ||| | | |||| |  |||  |||     |  | |   | |||| |    |  || | |||     || || ||||||  || |||  |||||| \n" +
                            "||     || | || |||||   | ||| | ||||||          ||/++++-+++-+-+-++++-+-\\|||  |||     |  | |  /+-++++-+----+--++-+-+++-----++\\|| ||||||  || |||  |||||| \n" +
                            "||     || | || |||||   | ||| | ||||||    /-----+++++++-+++-+-+-++++-+-++++--+++-----+--+-+--++-++++-+----+--++-+-+++-----+++++-++++++--++-+++\\ |||||| \n" +
                            "||     || | || |||||   | ||| | ||||||    |     ||||||| ||| | | |||| | ||||  |||     |  | |  || |||| |    |  || | |||     ||||| ||||||  || |||| |||||v \n" +
                            "||     || | || |||||   | ||| | ||||||   /+-----+++++++-+++-+-+-++++-+-++++--+++-----+--+-+--++-++++-+----+--++\\| |||     ||||| ||||||  || |||| |||||| \n" +
                            "||     || | || |||||   | ||| | ||||||   ||     ||||||| ||| | | |||| | ||||  |||     |  | \\--++-++++-+----+--++++-+++-----+++++-++++++--++-++++-+++++/ \n" +
                            "||     || | || |||||   | ||| | ||||||   ||     ||||||| |||/+-+-++++-+-++++--+++----\\|  |    || |||| |    |  |||| |||     ||||| ||||||  || |||| |||||  \n" +
                            "||     || | || |||||   | ||| | ||||||   ||     ||||||| \\++++-+-++++-+-++++--+++----++--+----++-++++-/    |  |||| |||     ||||| ||||||  || |||| |||||  \n" +
                            "||     || \\-++-+++++---+-+++-+-+++++/   ||    /+++++++--++++-+-++++-+-++++--+++----++--+----++-++++------+--++++-+++-----+++++-++++++--++\\|||| |||||  \n" +
                            "||     ||   || |||||   | |||/+-+++++----++----++++++++--++++-+-++++-+-++++--+++--\\ ||  |/---++-++++------+--++++-+++-----+++++-++++++-\\||||||| |||||  \n" +
                            "||     ||   || |||||   | ||\\++-+++++----++----++++++++--+++/ | |||| |/++++--+++--+-++\\ || /-++-++++------+--++++-+++----\\||||| |||||| |||||||| |||||  \n" +
                            "||     ||   || |||||   | || || |||||    ||    ||||||||  |||  | |||| ||||||  |||  | ||| || | || ||||      |  |||| |||    |||||\\-++++++-++++++++-/||||  \n" +
                            "||  /--++---++-+++++---+-++-++-+++++--\\ ||    ||||||||  ||| /+-++++-++++++--+++--+-+++-++-+-++-++++------+--++++-+++----+++++\\ |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||/+++++---+-++-++-+++++--+-++----++++++++--+++-++-++++-++++++--+++--+-+++-++-+-++\\||||      |  |||| |||    |||||| |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||||||||   | || || |||||  | ||    ||||||||  ||| || |||| ||||||  |||  | ||| || | |||||\\+------+--++++-+++----+/|||| |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||||||||   |/++-++-+++++--+-++----++++++++--+++-++-++++-++++++--+++--+\\||| || | ||||| |      |  |||| |||    | |||| |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||||||||   |||| || |||||  | ||   /++++++++--+++-++-++++-++++++--+++--+++++-++-+-+++++-+--\\   |  |||| |||    | |||| |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||||||||   ||^| || |||||  | || /-+++++++++--+++-++-++++-++++++--+++--+++++-++-+-+++++-+--+---+--++++-+++---\\| |||| |||||| ||||||||  ||||  \n" +
                            "||  |  ||   ||||||||   |||| || |||||  | || | |||||||||  ||| || |||| ||||||  |||  ||||| || | ||||| |  |   |  |||| |||   || |||| |||||| ||||||||  ||||  \n" +
                            "||  | /++---++++++++---++++-++-+++++--+-++-+-+++++++++--+++-++-++++-++++++--+++-\\||||| || | ||||| |  |   |  |||| |||   || |||| |||||| ||||||||  ||||  \n" +
                            "||  | |||   |\\++++++---++++-+/ |||||  | || | |||||||||  ||| || |||| ||||||  ||| |||||| ||/+-+++++-+--+---+--++++-+++---++\\|||| |||||| ||||||||  ||||  \n" +
                            "|| /+-+++---+-++++++---++++-+--+++++--+-++-+-+++++++++--+++-++-++++-++++++--+++-++++++-++++-+++++-+--+---+\\ |||| |||   ||||||| |||||| ||||||||  ||||  \n" +
                            "|| || |||/--+-++++++---++++-+--+++++--+-++-+-+++++++++--+++-++-++++-++++++--+++-++++++-++++\\||||| |  |   || |||| |||   ||||||| |||||| ||||||||  ||||  \n" +
                            "|| || ||||  | ||||||   |||| |  |||||  | || | |||||||||  ||| || |||| ||||||  ||| |||||| |||||||||| |  |   \\+-++++-+++---+++++++-++++++-++++++++--+++/  \n" +
                            "|| || ||||  | ||||||   |||| |  |||||  | || | |||||||||  ||| || |||| ||||||  ||| |||||| |||||||||| |  |    | |||| |||   ||||||| |||||| ||||||||  |||   \n" +
                            "|| || ||||  | ||||||   |||| |  |||||  | || | |||||||||  \\++-++-++++-++++++--+++-++++++-++++++/||| |  |    | |||| |||   |||||||/++++++-++++++++--+++-\\ \n" +
                            "|| || ||||  | ||||||   |||| |  |||||  | |\\-+-+++++++++---++-++-++++-++++++--+++-++++++-++++++-+++-+--+----+-++++-+++->-++++++++++++++-+++++++/  ||| | \n" +
                            "|| || ||||  | |||||\\---++++-+--+++++--+-+--+-+++/|||||   || ||/++++-++++++-\\||| |||||| |||||| ||| |  |    | |||| |||   |||||||||||||| |||||||   ||| | \n" +
                            "|| || ||||  | |||||/---++++-+--+++++--+-+-\\| ||\\-+++++---++-+++++++-++++++-++++-++++++-++++++-+++-+--+----+-++++-+++---+++++++++++/|| |||||||   ||| | \n" +
                            "|| || |\\++--+-++++++---++++-+--+++++--+-+-++-++--+++++---++-+++++++<++++++-+/|| |||||| \\+++++-+++-+--+----+-++++-+++---+++/||||||\\-++-+++++++---++/ | \n" +
                            "|| || | ||  | ||||||   |||| |  |||||  | | || ||  |||||   || ||||||| |\\++++-+-++-+++++/  ||||| ||| |  |    | |||| |||   ||| ||||||  || |||||||   ||  | \n" +
                            "|| || | ||  | ||||||  /++++-+--+++++--+-+-++-++\\ |||||   || ||||||| |/++++-+-++-+++++---+++++-+++-+--+----+-++++-+++---+++-++++++\\ || |||||||   ||  | \n" +
                            "|| ||/+-++--+-++++++--+++++-+--+++++--+-+-++-+++-+++++---++-+++++++-++++++\\| || |||||   ||||| ||| |  |    | |||| |||   ||| ||||||| || |||||||   ||  | \n" +
                            "|| |||| ||  | ||||||  ||||| |  |||||  | | || ||| |||||   || ||||||| |||||\\++-++-+++++---+++++-+++-+--+----+-++++-+++---+++-+++++++-++-+++++++---+/  | \n" +
                            "|| |||| ||  | ||||||  ||||| |  |||||  |/+-++-+++-+++++---++\\||||||| ||||| || || |||||   ||||| ||| |  |    | |||| |||   ||| ||||||| || |||||||   |   | \n" +
                            "|| |||| ||  | |||||| /+++++-+--+++++--+++-++-+++-+++++---++++++++++-+++++-++-++-+++++---+++++-+++-+--+\\   | |||| |||   ||| ||||||| || |||||||   |   | \n" +
                            "|| |||| ||  ^ |||||| |||||| |  |||||  ||| || ||| ||||| /-++++++++++-+++++-++-++-+++++---+++++-+++-+--++\\/-+-++++-+++---+++-+++++++-++-+++++++--\\|   | \n" +
                            "|| |||| ||  | \\+++++-++++++-+--+++++--+++-++-+++-+++++-+-++++++++++-+++++-++-++-+++++---+++++-/||/+--++++-+-++++-+++-\\ ||| ||||||| || |||||||  ||   | \n" +
                            "|| |||| ||  |  \\++++-++++++-+--+++++--+++-++-+++-+++++-+-+++++++++/ ||||| || || |||||   |||||  ||||  |||| | |||| ||| | ||| ||||||| || |||||||  ||   | \n" +
                            "|| |||| ||  |   |||| |||||| |  |||||  ||| |\\-+++-+++++-+-+++++++++--+++++-++-++-+++++---+++++--++++--++++-+-++++-+++-+-/|| ||||||| || |||||||  ||   | \n" +
                            "|| |||| ||  |   |||| |||||| |  |||||  |||/+--+++-+++++-+-+++++++++--+++++-++-++-+++++---+++++--++++--++++-+-++++-+++-+--++-+++++++-++-+++++++\\ ||   | \n" +
                            "|| |||| ||  |   |||| |||||| |  |||||  |||||  ||| ||||| | |||||||||  ||||| || || |||||   |||||  ||||  |||| | |||| ||| |  || ||||||| || |||||||| ||   | \n" +
                            "|| |||| || /+---++++-++++++-+--+++++--+++++\\ ||| ||||| | |||||||||  ||||| || || |||||   |||||  ||||  |||| | |||| ||| |  || ||||||| || |||||||| ||   | \n" +
                            "|| |||| || ||   |||| |||||| |  |\\+++--++++++-+++-+++++-+-+++++++++--+++++-++-++-+++++---+++++--++++--++++-+-++++-+/| |  || ||||||| || |||||||| ||   | \n" +
                            "|| |||| \\+-++---++++-++++++-+--+-+++--++++++-+++-+++++-+-+++++++/|  ||||| || || |||||   |||||  ||||  |||| | |||| | | |  || ||||||| || |||||||| ||   | \n" +
                            "|| ||||  | ||   |||| |||||| |  \\-+++--++++++-+++-+++++-+-+++++++-+--+++++-++-++-+++++---+++++--++++--++++-+-++++-+-+-+--++-+++++++-/| |||||||| ||   | \n" +
                            "|| ||||  | ||   |||| |||||| |    |||  |||||| ||| ||||| | ||||||| |  ||||| || || ||||\\---+++++--++++--++++-+-++++-+-+-+--++-+++++/|  | |||||||| ||   | \n" +
                            "|| |||| /+-++---++++-++++++-+----+++--++++++\\||| ||||| | ||||||| |  ||||| || || ||||    |||||  ||||  |||| | \\+++-+-+-+--++-+++++-+--+-++++++/| ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| ||||| | ||||||| |  ||||| || || ||||    |||||  ||||  |||| |  ||| | | |  || ||||| |  | |||||| | ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| ||||| | ||||||| |  ||||| || || ||||    |||||  ||||  |||| |  ||| | | |  || ||||| |  | |||||| | ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| ||||| | ||||||| |  ||||| || || ||||    ||||\\--++++--++++-+--+++-+-+-+--++-/|||| |  | |||||| | ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| ||||| | ||||||| |  ||||| || || ||||    ||||   ||||  |||| |  ||| | | |  ||  |||| |  | |||||| | ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| ||||| | ||||||| |  ||||| || || ||||    ||||   |\\++--++++-+--+++-+-+-+--++--/||| |  | |||||| | ||   | \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| |\\+++-+-+++++++-+--++++/ || || ||||  /-++++---+-++--++++-+--+++-+-+-+--++---+++-+--+-++++++-+-++--\\| \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| | ||| | ||||||| |  ||||  || || ||||  | ||||   | ||  |||| |  ||| | | |  ||   ||| |  | |||||| | ||  || \n" +
                            "|| |||| || ||   |||| |||||| |    |||  |||||||||| | ||| | ||||||| | /++++--++-++-++++--+-++++---+-++--++++-+--+++-+-+-+--++---+++-+--+-++++++-+\\||  || \n" +
                            "|| ||||/++-++---++++-++++++-+---\\\\++--++++++++++-+-+++-+-+++++++-+-+++++--++-++-++++--+-++++---+-++--++++-+--+++-+-+-+--++---+++-+--/ |||||| ||||  || \n" +
                            "|| ||||||| ||   |||| |||||| |   | ||  |||||||||| | ||| | ||||||\\-+-+++++--++-++-++++--+-++++---+-++--++++-+--+++-+-+-+--++---+++-+----++++++-+++/  || \n" +
                            "|| ||||||| ||   |||| \\+++++-+---+-++--++++++++++-+-+++-+-++++++--+-+++++--++-++-++++--+-++++---+-++--+/|| |  ||| | | |  ||   ||| |    |||||| |||   || \n" +
                            "|| ||||||| ||   ||||  ||||| |   | ||  |||||||||| | ||| | ||||||  | |||||  || || ||||  | ||||   | |^  | || |  ||| | | |  ||   |\\+-+----++++++-+++---+/ \n" +
                            "|| |||\\+++-++---++++--+++++-+---+-++--++++++++++-+-+++-+-++++++--+-+++++--++-++-/|||  | ||||   | ||  | || |  ||| | | |  ||   | | |    |||||| |||   |  \n" +
                            "|| ||| ||| ||   ||||  ||||| |   | ||  |\\++++++++-+-+++-+-++/|||  | |||||  || ||  |||  | ||||   | ||  | || |  ||| | | |  ||   | v |    |||||| |||   |  \n" +
                            "|| ||| ||| ||   ||||  |||\\+-+---+-++--+-++++++++-+-+++-+-++-+++--+-+++++--++-++--+++--+-++++---+-++--+-++-+--++/ | | |  ||   | | |    |||||| |||   |  \n" +
                            "|| ||| ||| ||   ||||  ||| | |   | ||  | |||||||| | ||| | || |||  | |\\+++--++-+/  |||  | ||||   | ||  |/++-+--++--+-+-+--++---+-+-+-\\  |||||| |||   |  \n" +
                            "|| ||| ||| ||   ||||  ||| | |   | ||  | |||||||| | ||| | || ||\\--+-+-+++--+/ |   |||  | ||||   | ||  |||| |  ||  | | |/-++---+-+-+-+--++++++-+++-\\ |  \n" +
                            "|| ||| |||/++---++++--+++-+\\|   | ||  | |||||||| | ||| | \\+-++---+-+-+++--+--+---+++--+-++++---+-++--++++-+--++--+-+-++-++---+-//+-+--++++++-+++-+\\|  \n" +
                            "|| ||| ||||||   ||||  ||| |||   | ||  | |||||||| | ||| |  | ||   | | |||  |  |   |||  | ||||   | ||  |||| |  ||  | | || ||   |  || |  |||||| ||| |||  \n" +
                            "\\+-+++-++++++---++++--+++-+++---+-++--+-++++++++-+-+++-+--+-++---/ | |||  |  |   |||  | ||||   | ||  |||| |  ||/-+-+-++-++---+\\ || |  |||||| ||| |||  \n" +
                            " | |\\+-++++++---++++--+++-+++---+-++--/ ||||||\\+-+-+++-+--+-++-----+-+++--+--+---+++--+-++++---+-++--++++-+--+++-+-+-++-++---++-++-+--+++/|| ||| |||  \n" +
                            " | | | |||||\\---++++--+++-/||   | ||    |||||| | | ||| |  | ||     | |||  |  |   |||  | |||| /-+-++--++++-+--+++-+-+-++-++---++-++-+--+++-++\\||| |||  \n" +
                            " | | | |||||   /++++--+++--++---+-++----++++++-+-+\\\\++-+--+-++-----+-+++--+--+---+++--+-++++-+-+-++--++++-+--+++-+-/ || ||   || || |  ||| |||||| |||  \n" +
                            " | | | |||||   |||\\+--+++--++---+-++----++++++-+-++-++-+--+-++-----+-++/  |  |   |||/-+-++++-+-+-++--++++\\|  ||| |   || ||   || || |  ||| |||||| |||  \n" +
                            " | | | |||||   ^|| |  ||\\--++---+-++----++++++-+-++-++-+--+-++-----+-++---+--+---+/|| | |||| | \\-++--++++++--+++-+---++-++---++-++-+--+/| |||||| |||  \n" +
                            " | | | ||||\\---+++-+--++---++---+-++----+++/|| | || || |  |/++-----+-++---+--+---+-++-+\\|||| |   ||  ||||||  ||| |   || ||   || || |  | | |||||| |||  \n" +
                            " | | | ||||    ||| |  ||   ||   | ||    \\++-++-+-++-++-+--++++-----+-++---+--+---+-++-++++++-+---++--++++++--+/| |   || ||   || \\+-+--+-+-++++++-+/|  \n" +
                            " | | | ||||    ||| |  ||   ||   | ||     || || | || || |  \\+++-----+-++---+--+---+-/| |||||| |   ||  ||||||  | | |   || ||   ||  | |  | | |||||| | |  \n" +
                            " | | | ||||    ||| |  |\\---++---+-++-----++-++-+-++-++-+---+++-----+-++---+--/   |  | |||||| |   ||  ||||||  | | |   || ||   ||  | |  | | |||||| | |  \n" +
                            " | | | |\\++----+++-+--+----++---+-++-----++-/| | || \\+>+---++/     \\-++---+------+--+-++++++-+---++--++++++--+-+-+---++-++---++--+-+--+-+-++++/| | |  \n" +
                            " | | | | ||    ||\\-+--+----++---+-/|     ||  | | ||  | |   ||        ||   |      |  | |||||| |   ||  ||||||  | | |   || ||   ||  | |  | | |||| | | |  \n" +
                            " | | | | ||  /-++--+--+----++---+--+-----++--+-+-++--+\\|   ||        ||   |      |  | |||||| |   ||  ||||||  | | |   || ||  /++--+-+--+-+-++++-+-+\\|  \n" +
                            " | | | | ||  | ||  |/-+----++---+--+-----++--+-+-++--+++---++--------++---+------+--+-++++++-+--\\||  ||||||  | | |   || ||  |||  | |  | | |||| | |||  \n" +
                            " | | | | ||  | ||  || |    ||   |  |     ||  | ^ ||  |||   ||        ||   |      |  | |||||| |  |||  ||||||  | | |   || ||  |||  | |  | | |||| | |||  \n" +
                            " | | | | ||  | ||  || |    ||   |  |     ||  | | ||  |||   ||        ||   |      |  | |||||| |  ||\\--++++++--+-+-+---++-++--+++--+-+--+-/ |||| | |||  \n" +
                            " | | | | ||  | \\+--++-+----++---+--+-----++--+-+-+/  |||   || /------++---+------+--+-++++++-+--++<--++++++--+-+-+---++-++--+++--+-+--+---++++-+-+++-\\\n" +
                            " | | | | ||  |  |  || |    ||   |  |     ||  | | |   |||   || |      ||   |      |  | |||\\++-+--++---++++++--+-+-+---++-+/  |||  | |  |   |||| | ||| |\n" +
                            " \\-+-+-+-++--+--+--++-+----++---+--+-----++--+-+-+---/||   || |      ||   |      |  | ||| || |  ||   ||||||  | | |   |\\-+---+++--+-+--+---++++-+-/|| |\n" +
                            "   | | | ||  |  |  || |    ||   |  |     ||  | | \\----++---++-+------+/   |      |  | ||| || |  ||   |\\++++--+-+-+---+--+---+++--+-/  |   |||| |  || |\n" +
                            "   | | | ||  |  |  || |    ||   |  |     ||  | |      ||   || |      |    |      |  | ||| || |  ||   | ||||  \\-+-+---+--+---+++--+----+---/||| |  || |\n" +
                            "   | \\-+-++--+--+--++-+----++---+--+-----++--+-+------++---++-+------+----/      |  | ||| || |  |\\---+-++++----+-+---/  |   |||  |    |    ||| |  || |\n" +
                            "   |   | ||  |  |  || |    ||   |  |     ||  | |      ||   || |      |           |  | ||| || |  |   /+-++++----+-+------+---+++--+----+---\\||| |  || |\n" +
                            "   |   | ||  |  |  || |    ||   |  |     ||  | |      ||   || |      |           |  | ||| || |  |   || |\\++----+-+------+---+++--+----+---++++-/  || |\n" +
                            "   |   | ||  |  |  || |    ||   |  \\-----++--+-+------++---++-+------+-----------+--+-+++-++-+--+---++-+-++----+-+------+---+++--+----+---+/||    || |\n" +
                            "   |   | ||  |  |  || |    ||   |        ||  | |      |\\---++-+------+-----------+--+-+++-++-+--+---++-/ ||    | |      |   |||  |    |   | ||    || |\n" +
                            "   |   | ||  |  |  || |    ||   |        ||  \\-+------+----++-+------+-----------+--+-+++-++-+--+---+/   ||    \\-+------+---++/  |    |   | ||    || |\n" +
                            "   |   | ||  |  |  || |    ||   |        ||    |      |    |\\-+------+-----------+--+-+++-++-+--+---+----++------+------+---+/   |    |   | ||    || |\n" +
                            "   |   | ||  |  |  || |    ||   |        ||    |      |    |  |      |           |  \\-+++-++-+--+---+----/|      |      |   |    |    |   | ||    || |\n" +
                            "   |   | ||  |  |  || \\----++---+--------++----/      |    |  |      |           |   /+++-++-+--+---+-----+------+------+---+\\   |    |   | ||    || |\n" +
                            "   |   | ||  |  |  \\+------++---+--------+/     /-----+----+--+------+-----------+--\\|\\++-++-+--+---+-----+------+------+---++---+----+---+-++----+/ |\n" +
                            "   |   | |\\--+--+---+------/|   |        \\------+-----+----+--+------+-----------+--++-++-++-+--+---+-----+------+------+---++---+----+---+-+/    |  |\n" +
                            "   |   | |   |  \\---+-------+---+--->-----------+-----+----+--+------+-----------+--++-++-++-+--+---+-----+------/      |   \\+---+----+---+-+-----/  |\n" +
                            "   |   | |   |      |       |   |               |     |    |  |      |           |  || || || |  |   |     |             |    |   |    |   | |        |\n" +
                            "   |   | |   |      \\-------+---+---------------+-----+----+--+------+-----------+--++-++-++-+--/   |     |             |    |   |    |   | |        |\n" +
                            "   |   | |   \\--------------+---+---------------+-----/    |  |      |           |  || || || \\------+-----+-------------+----+---+----+---+-/        |\n" +
                            "   |   | |                  |   |               |          \\--+------+-----------+--++-/| ||        |     |             |    |   |    |   |          |\n" +
                            "   |   | |                  |   |               |             |      |           |  ||  | \\+--------+-----+-------------/    |   |    |   |          |\n" +
                            "   |   | |                  |   |               |             |      |           |  ||  |  |        |     |                  |   |    |   |          |\n" +
                            "   \\---+-+------------------+---+---------------+-------------+------+-----------+--++--+--+--------+-----/                  |   |    |   |          |\n" +
                            "       | \\------------------+---+---------------+-------------+------+-----------+--++--+--/        |                        |   |    |   |          |\n" +
                            "       |                    |   |               \\-------------+------+-----------+--/|  \\-----------+------------------------+---+----/   |          |\n" +
                            "       |                    |   |                             |      \\-----------+---+--------------+------------------------+---/        |          |\n" +
                            "       |                    \\---+-----------------------------+------------------/   \\--------------+------------------------/            |          |\n" +
                            "       \\------------------------/                             |                                     |                                     |          |\n" +
                            "                                                              |                                     \\-------------------------------------/          |\n" +
                            "                                                              \\--------------------------------------------------------------------------------------/";

    static final String DAY5 = "vVuyYJjUzzZPxXqQpZmgGMvNmMyYTtnVkWwKVvOosmTtMSkDdKFsSpEePhXEexzZtnNTHEeGoOmMRrGggfnSsNwWVvJGgFEefpPMmeUuEoOBbjJuUwWjhHUMmjYyJsGgiISEepjJGgPZQqoOzyKkvVWXxhpPFcCfreESsjJRHUCcXGyYgQXxCcqXfFvVcCLlxxuwMmzZkEeoOKHhHLzZFflhiIOoHhjJaAJjYyHJjhmMYUwSsWUNxXUuFfuKkbBUtTnJjuNeEYyTxXtYyeIixXsvVQqSbBrRIifIpwIFfiWPiFEnaPFAafkKaKkAFfptTTtACHhWwcKkBbwgLlfFGWPUuJiIZFfznNjvVpSAaQqsnRrOojJsdmMDSVvRfFWvVwhaAHrwWUyYugAqQqQaGEexIiXVWwvbcCcCOaAoqQBLlNtEsSyYntTNeTHhZnNzJjSsCcWgGjJwzeEZaqQHtThALlBbnNJHhjbGgRrBPrReUcmPpMqQCuWwNnELlfzHhrRZKkhHFqQXxmjJMIiUuGgfRrNtTWzZwnlGgSssSsSrROoGguUdDkKwWyYghHGLDdxXoxXOFftdOoRSsrfFArRaSsELleDdDtRqQTJjtCSscjJbBrTevVrRQqDZzdYyYYyrkKRywWHRrhgUuGdDspPSrbBRZzYmMoOjJTtySszZiIzZdXxEeDJjUIizZuUuUVvucdiCcILHhHGgoOcCZnNzRVvWqwWfFexXEEeQwGgrBbhaZzAfFnDBBzZbIibOaAQqooOOqlLuUVvnvVNQWwoqDdgUuGSsSZzLlsQrRGgfFBbdnNOoNyZzYEelLcCPjJxXpHUuhPuUpPWsSGgRzZpPjlLJrUTtuweqQExXcCfFwlLtXxTWFGgRrcCiBbIKkHgMPpmkKGwWhgcloOxXBbnYyNlLLeDdmOoeEMEnTtNuUtTjJIlpPLQUuqmeEuUMCWwciuUnNnYyNfBbObBoFynNYCCkKoTtOFXxZzugIiGStTsGgxXBbFfiIULtTpPSslCcfnNYtTxXycAaaALtTlUrWwhHWWwwcCRhHEZzdDeJjoONKkAaWwXxntrRTROortzdDZTuiIAagGrRCYysScXLlYyTtxUudDzZHaAmdPmSsvVMpsETteDdScPpCDfFwWQqrBbRBbBTUeEutEeTtKkpsSPCcAglLGQqFKkNnsSfbBOowWahHPRCcrpaAvVCPpcnNetTqQEnNLuUlExVAaBZzbvXvVHheWwRkRrKQqYyFUufrRbBrHejJEhdSsDGgqMUuuUmtTiInNPLyYDigGIHhdlpnNgGANzTtZIinYoOZzaAyVvYyXxpVeEMMmmDjJdVvqwgGXfFxMjJmWQdDLlkKvvpwWPRrxXMmAyYaVEuUnNgGotTYyMmvcCVOYyYcCEeEeVvsSmMdDyKkjJWQqSsLlqQwemePpEMtiITPagGQZzqUrRXxhHxjJXuUuQzZKKkrRmMKvVpPkzzZqQZkjzZRrZxVvRrcCMmcCTnNBbNYynqQEQqetXxTtWwXzJvFfsKkFfHIpPihSVktTCcWwKDPDdpPpDOrhHCcRTMmEeTttDdwWZhHZzaAoeEgGOGgzZtTzoPRrRrFgGflJjFfGgBbEtXxTewWKkLnpPiIlLdDNphtJjnNAaDdVGgvfFwWvVOyYqqQdDRrjJQoMmeETOojsxXkKdSTwvVWtPBbpsqpTtPQtThsSCsScHmSGgpPtTtTIiPpdDDdDAnNadseEMyYDiIFfiMHhpPOHJjfXxyYFJjKKkkiHhHhnYwWaYyAlLxfFXyEetTNlfEeFLSmMxXecnNAaCAaJJjYykIWwnNfFifFdDkKKlLVvpPolLkoOaAKZEAhHYbByyYaVvNXxnTdDmMjJtDdzRrkKTtZBbeOrBbRoiIyYRrIcCbBCcUuiIYyDdFfHdDjxiICluULlMmLwWiIqQvVrLTtlRnDdVvwWNoDdOsSkKqoOQiIcEebBkKZzSOosJhHdDeEmMvfFXYyxzZlolLBbOLGgjDdaBbAfFJVvGnNgtSsAaaAyGgxXYGNncrSaAsJjRcpjJkAaAaKPVvCkwWKiIjJlLCgMmBbQqQlLiItToEeOwbdBAaFfNmPpirRgGIMoOXnNeExZkKznJjWrNMmmMSsHFMmfBbBbgGfSsOoFJjaNnMmKuUkAFfXxrPCceEpdDFfKkRhLluUnNnbBXIoOiyYvVcCXxxSrRaAJXxjslhHmMLXkKOSsiaAIUuuUUuLliIbBuUUuEetTUuWYyuUqXRUuFfPpMmlrKkRkKLrtTlLVOBbovyYxjnNdDWfWweEFwubBUJMiIOueECcAWwrRSsxXiIayYUPpmMicCEsSmMbGgBAaTXxtqgGQmMeAaFmMmMThHtfDdcCxXIFGgfPgGppgGiIrRIidDIzbhHBsJLljSJVvjZvdDKkzZXzVLlXvVjJxvIZVvzRwVvxXWUNKknRGgrIipWwPeEudDPpCVvcxXHhsxFfXlLSsHhmMHVbzZBvhSGgZzYySsgwLlYycwcTtCQqOoWChHWGMuUmKkgNnkKzZlLeElLLlGwZpPzzZRuUrgxXotTOoOZVvzcCCcKkDdPpRrFxXZXxMgGmqQKkxXzVvQqdvnHhgUuGNJsSjVDZzdlLgGDbEeBPpREbBAaeGpPvVgDQqCccCmMdTtVviIKkrAaNnBrRrrRSJjsJzZNnrRjRaAQqJMmpPXlLUcCuFfJIiQqjxBHhVrgGOoRQtEeTqjJwcCaAWvFlEeLuPWNnwpfXSsxkFfKWwFEOOooAaRdgGDrJnNjgGyCcYTLlWwKkZzWwteUkVvKhAarRHMrReEqPpQmfFrwDmMdmZznXxPpNMwWbBrBWdDwzZblIiLcyYCRkKRVZFfqQzXgGcCGggwBbDdtTLlJQUuqQOonLkKLlRLlrlNRrQqaAcrYyMmRQkKqCpPfFZzxXpxXRVvLkKlfFOoKkQqrwWPpdDPJjrrRMmRfZDAaJMqQmjPpPFkKMmfCIicpDdyYyCcYVtTvBbfBDdbqQFBbzFfZyJjiIozZDdoOOWGjZzFfpPcCVvJgwSsPpxXEebzZBddTtDcCXxNnDHhEdSuwgdamMAGgzZBMdDkKmdDVvSsIliILHhVvpPFeELJcCTtGgWDdwcvVClLeEDyYdtvVQJjQSlLspPBbDdlIiUurRLlKwWDAaRzZrKkHhSsdJEejkbBUNnsSfFzZJjxNnHYyOoYyZziIgGhxXJjGwgOoGpPHhWgRfFYRxXryjnYyoOYHhnNyNrjJRIxXiJrREeUbBurnbBNtmMTHLgGlOkKSsgtghHMxXmhcCHRrLlqjJQQCcqXxIiGMkhHKSrRoqBbgGfFQuRrUMHZCcKKkkzEelLxXhmMchTttSsLloOrRGgeGgtkKhHTlLEOgGpPoEeaOoKbKkYyBFfyYmMYTtxXTtynYyGgXDdFfFfxAifFIaNJjvLnNPpLXxCcWXcCpPVjJtTvbEeBxmSsQqYyPpZRrBlLbvVjUuRDRrPpJjuUicCGgxdDqhHeEkVvKRrJjtTSsEBboOxXHUyYRrkKWcCwuIiVvhJMmjGgCcdZzDhHDdGioOUJjlVvLqQuUuciIwWiIcCCJjQOoRrbBCQqsScxbBTOoKbBlLEekrRYCIiHhcOoaAFfyQYWwyqxtTUuyZfrRyYFkMmqTtQuPpUUXGgxlYTtycCFYyOrRoqjJQHhxXfKkcCukKzZSxqQXjpvVHVvRUNpPnfFcbBCuyYrkvVKceECJjEehHdDOooOhIincyIiYCNTYytRwWKEekMmMbEeyYfFuUQquUnNBUubBmPpLIXxilnJjMmAacxqQXiIqmpPMMmlBtHhIWwgJjiIeXZMmkKpPSspPBiIbZzpPAZzazNnwWMvVVbBvMYKkpPNxXnUuuEeUEcCeJjJjAKkVvahlLuXxUHJAajUuGgpPTWwxsUuzZkKfPpbBkKFxXlkerREKrRwWLlLkKSGumKkMUgcCXHxDYydPfFpOZzgGjJcCwWBbbyEeGedDeEdDvVEdcCBbssGlLFfUutLxAaXlToOgCheEHcZzEgGetGgOnphHPNoxXeEdDOoTteEoOTbBPpQGgqfFAWwaYcmMdIiYCcyDCONnFfoXxOoiIyYyEeuJVuUILliFLlUudSxcCbgGByYXzZNngGcXxCHNnTnBbEesPtTpGgnNSXxNdRrEeUYyVvueEEAaegGZzIuUiHhCcAaqZzQOoDxHqQhCcXbHNnhbBBrCcRiIKkdDYBjJbGgzZyZzsSvHhVjfFiIpSNnUuDPuUpdsauWwhHUOokKDdARDdQqIikNxXrRnmMPHhfFnNgGpKlLrlLwWLlJjIiPnfIiFYUmMOouFfyhkKHqQBaGgcbBCaQqdDAetTbUuBVvMmdDEmWMmwkKMdTZzZzgGdDgGgGiIdDlLhVoOvFfgGEeMUNWwmMmvVMuUbGrRIifqQLPpwWlRUurcjJchoqQONnyLRrlmMQqUpExXdDeEjLOoliLuyrRGgYPppPYyfFuYyQqLlSsUWwnNzZpEeyaLlmMAYPaAvVpxXPKkjRuUlJjyYLSssSvVrYygGRrgGJRrqQqdDQeElMmsSdxXMmDvUuVIiLUIdDDwWdojJOiIiuKkQrEeRqULZzlCcEekoOrRkGbBgKJjzOkKeyYEofFSsecakKAzZCuoOlddDDIBbiQqixXpPwWkXjJYyxIiKcCfKkWAatTzZnNBblrRoOtTLtTQqtfFTVvtTsSMrRMmmGgqqkKDdQQxXNnYgGywBbfyYPpraWwAEEezZeWdDdDuUwQqvbNsSnlLBkKkKtTpPVWYyzZaHhOMmtqQTnaANyebBEOoymMXrRxYywvQqfqQFYkKsSYylHhLHhLlTXxYygGFRrfwWGgvVOoUuSifFIMmLuUuUYXxylRbBXiIWwxreEBbMmuUuUNjJjJprRPIMnNmisSehHEvVqqQJjpPIijfFJnNQdDXxKcCrRZzqpPnNDEewWdfrRmMCZCczZzHhKkcjJCCcXxEeMaAiInZzNMaAmmLnNCdrRaAtTDLlYyTTpNnPAatBsDbCcfHhFBhHdGgKIdDiXPpxNnkxXAaAzBbZakKrOoGgRHXxhttVVvmMmcOoGzZGLlgORJdDKkjEegGsbBAUuvVaVgumMbBoxXbBVvSslLxXkKqbBnNsMmcEeCqQSQeEkIiKSsbBHIkKitTfFfFPwWkKLlbBFGgfEEJjlLzCcZkVvKYtTyRcPpTtAGNngaDdPWNnnqyYQhLltTbhHXxEeARlEeUoOuqoCyYTtciIOPpQwWLtTVvfzZVvvwWnNnCcjJOcCdDwFfOIVvTtkKfQqFmMfiIBbFEesaCcWwLlxUwZEQqePpbNnQqUuVEecCKZzBlLFfbvVQqYcCypPrAXxiHiIGSdDoULlGgTZzttTMmbdDBdDusNdyYDFfEXxPpHyfQqFPgGpVvtzBbkeEFfsFfSsHhKkSwWHhLltTFndDjoOJHhlLQqVvlLIilMmNnhHEPpeMIiAsSamWABbJjVvgGxXQFfAaXxhnNIimMJjIFDLldAuUNnTtaeEuULFflEkbvgGgGhPUuIUjJuRrRriPpOoeEpHInNqeEdyYtqQTCcNhZfFzTtWpsSPBbOoLlcCapgGHhHoOomMvVcCOuUhpPmMoNncCOGgNnHhFfVvAoOKkVvzZakNoOhaAGgpiaaOoVvxtTXAvVIcCiZRrzOsSrreGgnjJtTNuvVUxNfFLlpPxXXxuKkTqMmCcQtEeUSslLRMmUurQqjmMVvgGaYycCCMmkmdDMCzNhHnuUzZZcFbBDcCvVdfxXxTtXxpYykKpPJjxNnXqYyQFfbbIhHOoivVBBKkyYcCgFbBYZzkKmMPVvptTSsiFPdDNnhHjYEetwWwWiITyzZJpfiIMmNZznKkYiIyqQElLjAaXPprRqQNvVnxcCkcCKvZzOpPoXyYxhHOoFSsjAkKaCJjjJSQqMmFfZEnNeuWyPpabBAbsSBbcnPlGgLlLzZSBbtTsPeEGgzCcgGZlgGnNWuUwZztTEPnNOouUpqQwOmPpPpMOoWPbWwVDdWHvVhJtZzTjwMmsSvTtwrRWgGxNnXMmhHkKyYeEAaNGgJjzbBZHzZxmMsoOQqSlLXeEtCcMNnQqPpmwWVdDrqQmMRvVvLsZUusFfnaEeArbmMjJBRgGUeEuNdDrjJESQqsiILWwekKQEHheAazjJxOoEenkKNtTNqQrzuUZkVvmMmlMcCdqQDmxXSIisOtuUOgGoTRrCcuUvVdDcCnNnzZMmXxxXPnNbBpHhNHhrFjFfJJjfGkjgGJhjHhqdDFyryuzZyYAUsSuawWbfFVOovdDtTXBbCcfFfOolLFLlGgxpPuxXVtTDfFdYZzykXxDdKQQqgpsSPGqvVzZvTtvndyEeYqdSSsCcpPLlsrROoJJIinNjOojrRIiKxXmMMmkoZjiuUIJzEjJelwDdWLcCtTsjIiJJjJbBCDdcvzlLZAxXtTaUuFsJjuUIiIiyYyeEYiIfFSfIiwGgWKkSsGpPzHhSsokMmNnxoOMmJjPpMjJqpPiIQJjjHhgqQGwtTjmMzOmgGeEMRKkUKkKkYyzeEQUuBwWDcCdVvbeEqjJZCczZNnxkKFfHVkJibwWPpmEdDxHhjGsSgeSsvYyeEVsSErRLGOottTeEBbbBTnNmoOVcExbBNnVvXEegGwWWwwWVROorORroPpvLTEelAarkKqUuQiIjJjWwJosSOAjBryRfEvVeFrYRzQqtTYyNMmBbKmxezfeYygGRGgvVInNiYBHhgGoswTCctlLWSlrRjJVNOuvFfaqQuUEeDVvdfFLlcAYyIiJoOYytUuhUuTtZDRrYnNyIiDdQqBjJcRrgGCKJvCcUuyYRrARzZiIypUuiIPYwzZOWwHhoBbWrdbBbcKkCGKkEhHOoiIhEdDJjeyYnNcIieKkEhwWKwmTMmlLzoOZgxyyYYXREpPwWGgOyYvVoOojJLlOotThHRrAViIdDRrGRUWMqQWwfFZlLVnHhNvzbCcBkKJjFMmfjgGWwUIPpyYiEexeEbBCiwWfFIcLbBujJMyWVvwYmKkUfFYylcsVaAvSRxXrPjJpCoOIkmWwqQQqYyLliyYIAaxXeEMeHwSbBHhsRmMVmMfKkFRcCglYyRrdYyDJsSIiVvCcIWwiZzjDVXxvdSsEVvQqWweDdTtZzyYCcQqQTtxOyYtWwXnNvyYAYyaTDbQJjqIiLlBxXotcCJsSrRjHhuUaANvRrVnAuoOUaMmqFfEeQcCTaAOaYUBOhHofFXpPxZzbFqQceMoOuQLlmMqUKkroORmVvuUvGgOtirRFpPfITLlfFGtTgoUpMcjTtffFAQqrkKRkCLlcgGKxVKklRrLfFlTtEeFfbVlLvCctTlLqDdhNnHQAnNnNaaAaRrNZzNnBbndDpCCciOoIuUUAALRyiIsWJjTuVYhIipVvvVPMFfVvlPBbyrRpPeEYyYpzZpPXxuhHUwWLPpFSDVvdqLcClQiaAHJjIdDiiINnsSkSsqsSkTVvTIiPHhmqQMpSgGszXxZvJIiVtTvIvvVvBbVHhVULlgYjJfZzdzZLlyYhHHhGgzZWrRNklLKmMZznWwhOoJjQgGfsEeunNXxyYlhHcCjJLURSRaAryYVuUzUOAawRrRKkQqfFrWvVUuMmaDShHhJjoTtsSbsHhSdnNDEexXSWdaBbBbAFfELzZlTtxeQcCSsqCXJjxkKtEetpavqQVPYyKMmCcvVkUrPpREewnNWvVAhHNoOOopPZzngGdDCiVveEKkIinEejJNdDuUIcZzYyiIovlLSsVtTIieOzZMmoFZzfKzZkjOvGgetTOorReExUJjuTtsSAsSFfaXCDdchHrRKkXxhjJhIVvErReBZzFfiDdIbKGgOSsGgnNolLuZzoOQPLzbAaUuBqQtTiIREWwSsefewWaWAfFinGgNlnNcBbCrQhHqRCcjJLyYiIIrHeYOoygtTGOoRSsyyYLMmUumMXxFfsSsSFfpPIOoGKQqXxrRkgiTtLPpLlllHhYNmMTtPdgGktTKDhHkKLlpnszZSTtZzVDdhHmMjZNgchlLGKkIiyNmMiISsoOcOFhIiDduoNnuUZcCHAJjbhuHRrexCcXEhZzyXxYdDeEyEelfPpUPpkjczZCJTRrbrdDwWRfFBtTfHhMmFkfLSsXxHTthsJwXxWjCcZHhbBwYyjJciIUJxoOXjuCkDdwWxrRXiIBSYyuutTpnDjJdzGxwWwdDEeAlZCcXxzLuwWgGnxXNOowjMmzZwHhWJUZhHzhHudvpPVSsDrRSMmsNpPdatBPpqQAaRrbjeEUuJhFfHkKFfHQxXcKkmSsMLkLlBbUuKJUlfNnUuFuUVvNnFAQqsSZzHhqdDqtsSTLHhfUuqqxNnXbBXUuYyvCjRrJhwpPWHPBbDUudzeEZoOgGVEEaAlyEetHtThHyrRMmYnFEsSsSuDvVZXxFlLfzZiIzbBboQqQqyYOkOgGoKcCvEFaAfyYFfGgaXxlzkKvFfcCVCcnNIRriGWwgtvVjJyYOjJgGodxXoFfuFfFbkKjoOJBbBLloRcCrTtEejJBbUtIixbBxXQqXNnnNzJaAjZCvqQVJjcJxeEAaXWwZsUuXxSOoqQLlcCeESsVvEpPkOIiWwInNVviIiFijJtTuiIGghHhChxXZtTJhHyYjzmMnLydubBEchxXxpPXIVkjJGgKviWwqQipPICcyGfFgYyZecimMFfFeElxXbleFfQqEPpsrpPoOwuUWEgGeZzXHhxEeRwWeEulLLlUnCcPprpPjIpgGPpPxtTXVAajJZSsVvzzZYyqQxtQSsqdDFUuTlLtfubBEQqoOFfpPmMNnneHhJjEWxfFUuLlDdXwXxNlLeEdvVSsDtTEaAzqmMKkZLXvHhVEeYyxLMmlMfFfKkiIKJjcwCpcnNiHGghNJjkpPOmKKFfcCqEeTocCLlCcHTZVvaZzfFbBbmnNOXxfFoCLlqQIiJuUdFfSsFCcfrRrRDbfbQqqQlFfLTHUuxXUIiuhtIitTNnMpPfFgGbkKBmKXxevVJjlZpPzXnyOoJjDdwsSWxXDDNneEdoOJXxjNIpPsSpPOoindBPplLSJjsbzlLZSsMtTmYwWNdlLDVaAvDgGdGgDbPpBdAaxPXxpXZmMjJzLUbsSoLSsuUlOgGIxXnaAOofFlLhHNijhLpPKxXvVkCIoinNyYDdnNNneEZzIOSsIiCsSuUcwWpPMUlsSLuRXxrmcXxCGaAtjJrcCRbBcWwxsnZLlxXBbmYvqSBQqsvVexuoOLlMmUyYXWwXxSycgqzqEeCFfcYwWTgGcmltqQFfYKkLEMyYoOmDxkKrKkomRrrfFwCcsSmArdDRlRrBNOokMXxZoOJjxXapPAWCcwGsSZjRrEwWeEmRaArPoHhHGgJjaAhNnVkKyYNlCcQAYpPyazVJWwjZwvbBVWbgGPpxXBzBDiUuEoOKcCLliIGwkDdGxXZOehiZzRrQqAaIlwDbtLJjUUtTFffDdFUuGgutPaRrAPGgtTzxzZlLXSmMaRrQbBnQhmMrFfRpPoObsSSKnNuXxbBoLlOUNxduUDXnTfFeEUurjJaAjJjDdbVvjJBJGgvkUgGoOzbBKoahHABbOoOHtTLlNXvVvVhdDCcnvVsSFfenNuUbBpPZMmuUVvzkMZfFXNHhnRrvTtZzLlCOTtoSsEeBomzHCcsSqRrQycCOBbMdmMiIxiTtPCeEyyYYctqJrRQZVlLvrRVVHwkKaANIiAaiIdDdDTfCcFtnWtmkKvRrPxMsSmSYyWwgFVvfvGgVhHKkJjKofFOvDJjddDfFByJjYMmbOzgGCcNrVvPorXPQYyzZunNUoOqeEeEVvpHBRYyrzJjZArxiITkPpKvevtTQqTiIYyCdDdhHDbBbKkBaMmVDuUdMDYyEWwehHgGtQpxeEYRBOlLokHNnhMtTpPXlLxRGHWcCdDwJjSQMmkITvBFfbVzbBVvReLlMkEKMmkNneKkKEefmyYIiGyYgMOAEekKkKWwBfQqxXFtBbCcSoOVvuUiIGgyYDdNzPgGpinNMRrmfeUeEuEjWwJUutFLlfQqpPaqBbjdBbxXDTXxFdcbBYvVoOoLTLAmMEeyGRzZzZGgUmMJtmbBgiIGJbBESsyFTtaAzfFMmOoEkqEeaASpCcPautTGWwSxPXOjJoxVQBwWBbkKxXfFMmrRWKRYyIiTuUttoOjusSURJjrJPpqQDdWoOoEeRrfFkyYEXKgLwKavVAxOoklLWwpuaAUYyPnKkPpUffswWouUclLXxIiAaiIuYIrRiyglbBLTkKkJjhHrRFWwEeStTznPFfplLNfCUucSGgmDdSXxWeEGkKgCMtXgyYVPtQjbBJqQNnOyYoCNnHmMjaAtuUSkNJpPZgGzCcuUaAWwjeOoEgGQqQqSsnMGkeEYyuUBOoOgGowWbiINMpPnNbBGnWcCcCVQqVvYVWKdDkOoQEecExcChHuUAhHaXzZLlWwlMvfjJhPpHcyYaxXKqQkBjJbrRpolKZbgGIuUiBbPpBgsSGyvJjgGIlAaSewLbJDdvnGgaANVhUfeEaACKExXfAaRqAXxItvVTijJIUZzucRaAYqJrNXlLxYypNVMmvPjJkKpgGVvHSosSTzZEzZePpIieEtOaAUKklLFfYcCTtEyYYyOoIXAaVvvVlhHgkKXxdoODzZGgCcGLEeWwnnNEzPpVmLMbBqxxXIEeFfiXtkLlNnVlHNUunzZhPpgGHhZzwOoWTtYWmGwWgOnAaNzZomlLwewDBbOHPphewZDQaXTtHhwmMWqyYQLxXlweEeEWMkKzzZGgZDdvGgYyabBaAJjEeAmPpbBvXxxXiSKVvDdLxXlksDdnNcFPpfRhHHhThHtrCxlLcaACopPOtnNTsSnKoVvxXgQqGxXKeEtwaAXxMmvpdDtPIipXxYWwiGYDdWwxnUugZzpPFAcCPpXbBXWwGgWWdSswWDSZVnZzjSEnNRuUrUdDkKPpCcFfmMKYEqjJjzvSsVZPpWDddrLlgGRqnNQHAaAWHOJjBbNcCsPpeGVDaHeebBEuUWwuTtUEhrRgGwxiIXxFfpPOTtAaocYNwWpPLlEnYtNngGEetaJHvvvmMUIyYuPFcYtTBbAaGgyYXxZsZHtBZzUuIiaADdUxbBXrRuUPlLFfWwqQpwWLlTtgqidarRRrdDZzAsSgADvVemzLIuHhUsZzHhpPoxXvtTVrVvzZvwfFXxWmMtTVQqBbGlWwLgRFVEWwIaAjUuUuYSsyleOodTbBQqKkRwGFfgGgSUlLMmuvfFVyLlqQAaYoAaSNnOZWwjfFPFXxfAbBaaAZNnSscAQJjNnkKqatTtTHhbBhWHDdhvZzVwHChHhMSsmTXxtYoOyRTtrSFfdbVsSbfiILlSsQjJzzzoOZuUZDOOTfyZziHhPpcCIxXPylwWpPGgVvQjHhLnBPpbNAFfVvOfFoUtGggGoyMmDdfZEeTtzrNnoBsOCcjsSyfFAoOLllLasSnhHNYDdbBJCcoLltysgsSGSsaAaAdTyYYgGVAaBbuWwUvsSAQOdPpiIQoOCRWwSscCchYXxKkJIiuUjFtTGgfHhAaJJjlffXxFZzFKkKkitTqhHvzZYyBFfBLlbewWmWNnwFarRigKkCcNnybcuMmsTtqQfKkuOPpnNoUaANnxUdjjJVvgAoOkKfFaicCXUhHsktTbBkKWkprhVOobBbBYqQyAascCSbQfjcCJFrRbEXxeSsdhAaNnseAybBrRLlYatHhTUCceDdEWIiRaALsGgtLleAwkKWMXMNnmMzcCgGJjaABfFIiPpbRDvVdrgGUZEedXxxXDMmFfvVZChHQqaASsQhdbBDHSsmlLIidqMIithcCTtHklLjaoOAzmMZVvEeJQcCqBbDdEeLNqQxdpfFiIjJfFsSBzZqVvQwWmZxXOozMSfSsFtTxXXYlLdDsxjrmDkKPEevVplUuqNnaApuYysSQyvVYZGXxguIiqQUuVadDAxXLlcTtCvCcAXxXRrxbBzZQqCMaAmMzZIiVQqtfFyYazZALXxwFBMgdjJDCFUnNuCckKdHEehMfDdgGVvFzZhHCcgfuUZhhHHcTtIixXcotpUnNuPYRrnByAZzaYmMbNnKkPpLpPlPpWQpXxuoOyMmWDSsdBbfFzvVZzzZZXaaAAUuxHhMyeYyEWIBbidDwwfXxeEEeFzZDRrOcWdDDdphHInTVvicCItylfFLhFfHYNVvhHiUuAazZPxdDMLlBgGRrlLbicCIqrRDdRUutTdDlXxLcqrRQJRmJTtrOotTRtTVyYWwKkrXqeEkKGgWDdKkLlwPCmwcmMXtTpURvQohzguUJjGguUMaAmGPpZczTTttZkKcCcCevVOozZeECcEQuPPpOotSFhHXnNGgLlGgdDRrxrhHZteETzGgoOSJjktsDWwaLezAdIiDJRSsryvVBbjrtTiIQqRNnluUdDCSKTtphYyHPuUDXFNnqQMmAaDdGfLlyQgGEeOokKXSBbVvgqzLlXxeEIPnNctTFgGzZfbNnBwSsWObBoLlwWPIiJjdviiMmXSsXxxIArRPpaodDOVvpRrPPYEeybBqQQqwWbimMWweEVrRpPSsvnoOTsYySGxbHhChHcBsoOFfOctTJjgRrGCoKkOZPpFfUumMCiIPhlLOoHMpYmUuRpPrnNUyFrJdDuUmVoOqQideEnJgGhFfkKScQqCsugGNLlWlLDddoODOoNUunntZzVlkKOUqQuUJjEecHWXPXwWkijJezIyYsOrHhUuibBIqQRogJjyOMNuUaaAAnmryIzZCKYyQlJqQjeFzzZkLaVBzqQdDzfFAaOopPxXZDduMmBiIFfbUyBbuUYHzvVbHwCccHXxhzPbBWbTtJjDdSbBsBXsvMmGgVrRogRrGOZOFMKkpPmfiImMokVvXxlLKzUlLuCepPLlZzuTtictxkCcKXddZzsGcCVvWwgcCyYAanNOwWSRrYyVvaAVqQhIhEemMHOoxbBcCrCcBDOnNEsxtMmTGgOpxDdnnNHeEXuUxVvmMnNPphJNniIhUaesSNnNuiTtIGZeOgaAmXxMzZdLQqqgGjJsSeEOzLlQqbfTtWwxwhbOOElLAaYyHhzZJEOIfHhFFfgXlLEfiIaTGTfleLxUudyYDXJjxLGgjJLzZcuUClktTpPmVvRXDdaAnNzsSsYyGgDFfvVOoVvmXLAvUyOodpBbDdFNjJZzLlNuUHiAafgjTtgGphHsjJSPHhIFfFxXRrwcCmGHHhhxPszZZTtTtzXxxbNnenzBbOSsOoqcgGvVpUlSsXxfhHFCaAAanIioiIOOCHhczjJZCchjJHcaARiIIirpPrRhLDqQdBvjJSsVqQrBbxTtGcCQqgBbKkMzRrJjCcfTqfXmnoOSgGctJjTsZQSsqzuUvVfxinNcJjxXcCEeCKkfqQweEeEZzfTDdCuJjULkciAWwtNnuUAKkakKgGbBpOoPEiIcCoxrROoOoIaFfuUFJjGoOgErRMmycCXNnxKUueEtFfThUuNYySaAQqjJscyQqRrQpBbPlvVLsSsutTLFflthHSscrRCTUFfkGgXIpPOoxqQXMPFfYoOEDdebBgGlDYyhHAabrRBbZzBjJdDeEIidKQqMmcyAUusSSbkVvtGSsIVbcMmvbDBOohHbdXpXuUKCcBSkKRjnSsNjqEeqeCsSRbBpLOBLlMmbqmmNngoOGOxNyYniYwWSrNnRsyUurxxXIiVvSsJjKOoiIIixMZzSpsSuaAKkUMQFfBvVbTtRrJVvAaYttLCclhQnNqzZeZBAaEcCxXebzqQdDWMyYUuySsROzZozwNnWZHSBbhqtTQTOEduQTtcCGkTtKQqDdMkAWwaHhfFZZzeypPPpBaAuDdiUuaApPfUuFZgGzZPybBSsYXPDUuEflLdDTtFkXxCmOWwnQqKkpuUPfFNJvRlmnWIRiEfFdHGdDIictZzdmMDnAaNrnNRSjXxfFOoULlnNnNubBkbFvVPShuNnUuvuUuUREbJjBeLlYydyYDNUtTuCcsdiIDyyYYEetTILVvnnNzzZJJBbOjJeEJMdDsSBIuUdriDdfFXxOmPUufSjpbBPELzDdRrOYyoJpPOxQBbVvcHNcVJjkKosSocCOqQOEEeeghHqQwLCMxaAuUXhHTfFxXnjJNLVaLjZolLOzFTtfyYMETtuiFEeJjlLevVEbspVvPEEeeESsgAAaSsaAlzgxFfQvPPPYnqZTtuvgOoMGBbFfpZrRzHaAnwtApPaTriSEehBbWRrwbtWAaEeSsTVjEephHdpPIiiIuExeEQusDdRXeKAacCFfJFyOoYQqFfZzrebiIuUBNnEGgSqbGguUTtBrRfurFfRUFsWwOLlOWkKnNTXJZfFCczxwWXiIjueEdlLDBPtTpgGTBMmlLtTuaAjJiHhFbBSsSwtTBbWLlADdmMxkKDekoObBQqKCcJClSeEdgwWGqogaAGnPphuqCDnNwbmMBWOyYoXxfjasGiLlYsSEiIerRQqeWwEvqQfLfFlFaNCREjJaAgfUbBuqQIMeEIoaAIGgsjJSiXyCnjWozZtWwuVFfhyEeoEnNfFHAbBmvVQIikWHhwAGMwWmKkPlrOoYyPpBlqeELlrRGgQDeUtWzZwpzZPcCEPNeEnpeaJjkrwRrhvVSsRNnxsoOSLmZzMjptTOoXnvVaAcOlLAlLaxRrFfpkKPXQqmMBbxYErRLobBOuUeCQqAacTPptocCObtTBgGdVFaKSFfmMkVNnMkKnkOoVvKfQqGhBbHgLletTtBbTElHhLkMxQqXaAtWwAmKkAmRrMPiaAIsSIPfkPoOBbivMmOCceEvHhHhQJjrdNnwEeDCcoOdTtIYwWzMpPGNngmUuhSJjsvVHhwWnqsSfnNzEBHapNGgJNzZaPpLKVvtIiWVvwTeEwxgtoYPFfXiIRnrRGJjNaWGgsQzZoObBYCcOEenbBMQUuqVvmiGkKtTuQqUBcCaAVvvGgyYGNnvVzZeEfOJFEVvOoPfnNUUuuCcnIjJGgeqEeSZziiIILlFfmvYyqzRGgIyYiwWpciIRzWoMbIBbpMmtzZMzZybFfHfRZLCLlPpcThqmvDsuUSsSdKkXDuyZzSshHYUdxuUXnNXxYxkKXFTnDJjSjnzZbBtPuUcfVEevqyjmKkuUeWXxwUWnNphLlHPBbpPWyTtJjYwwuExqOohDdHvWwVZzRrIPpEwOoLYsWMDSsdpPHicCuITtndDLlNiVvUtsxXlIRrHukKyCcIiMmgGsPpvlLDDYydbdDBbFfWwDdfdqQZyTtlLcaFfqQNnNeZJLEdRdsSJYkkKrzMlHhMksJYHhyjSRrEOCcNnNXOoihvVjJjuUuUEWTtwfMmhHaUurBpPpPkvKkVKbPsSbTtPitTAsSYwiIpPFfMjeOoEmyYMttTTXQtTMbxzZPAatyYTFfpUSHhfFrRNghHGnpswZTXAnMmVMmvFZMRlLrfkKZaeaAEAZzOozYbuOoUuMXxmgwyYWGUqcFfmMtqQJZtTFFfLBjQonFcCMmYtTyfGIiGEeDXxxfFPEepdDpOqGgnRpUubDdBzZWwSwWsMmTVvtCcOojDUupPMmLIiyaAEneEGgNTwWkwWKQcCtTCKkccJZfFzCRddwuUWNnjfFJDNOyjGgXxJmtTSBtNeoOvVMZExkKXTOkKowZiJnUueEZHHhpiEekKIcnNamMAqQCyEeFfYgGfIUuuiDQhHYTJjsljJLRNUJjvkuQqUKVYmMOzZowGrfFBgGhxXixkKXbBIHJjaspKkPVnNxGgXHaAGgecCcuPRsBQqkKQVtTJjvoGgGgyYHaAoOhElHhQawWAqhMmDFWbBKkwmMLloDFrmyPDdrdbBWwjJXxeIiXxHLlcCSsiOoAaIinNtTSTtSsaEMmAapjJWwsSPYyGvVdAaDsSKPgymoWwSHqgANnaHDdvBzZbVqQhtTsVTtvcCniltVqQOorRdwVljhHkeEYyNKknDNndKjrlNnPFmMCPsYyZzhfWBbqRrQHFfhARrajFGgfWncgSsGMJjRrFNnkKvVqxuIiKSRnttTAyYaACcacCbgBSsVmswWOogXBvAUuFfaYinNNdDnOhHUuoPplILVuUVMLlqaNAdDaIPLlanNAKkkKDhZVSsrOHxtToVYyviZMmpPIzZIRSsrRLmrDdBbcCDdzxXfAaMmtJjTDdyAbyYTuUtCLDwvXqRmRBcfXxMqWHhwhlCcCcGrLASsjJbBVqWuBzWkKwZTghcGgJEecCYymESCcGgsQqKdDtTocCOvLwoOwrRsbsZgDnNcBVvTtnNbqKkpPclIvPCNIbgGDJjZBgcwWCcLYnNxMmXPpPeEZzpXUSgGyYTtPzZbByYMSsLoOAqQOsSSsIFgGcdydDYypPYMrRavJjiPpbBIVFfNfdDDDkSsKMRDrbBRdrzZtDYsKkkTGKlMmLlLLyYIiRrUmcZRMUuWKGgQqDdTtbbBBgGcnjJkKcrOohVvsGgxwWmjJIMmoOWGgFFMOCaAYygRbhbxXDdBHiVgGBDdnfqsSuUQrcKQquqQgQVLlPpvyxhWfUuDrgrRGVfFBPpbeLsSoIiOwWYxXcWGdDMwzoOZoIiNYyJoOeaRTQWWwdhHGgTtDaOoMRrvedrCcRrlhljJVqYpruWcvKkVDCUtTlFneEaAdVvHhDbBDdjiIrMmRDLlIHBbNsSnKRKkrYgdLJfdoTmrnFfdqQNdDBtCcIieEhHTbxzZXBTGgtGnNpwTcavViIeErRHhfFQqACqQEsBbyzZoKkuZzUTbuPpjJsFfBGgegbpiRrSMmPpExqQaCcbPdDBbZKIgLwSilbubBenjpPCcWiIiIoOEejDdJIdDfLXQLVvGgzZEevoOVxXsmtjJTMqIdDkKiQyEkKoYRrykKOViuUuFfvOoAaIiCyYgEtFmUusSMCKTSZGgPGPpgpPgGprvSsOoyYohsvyYghHXxGPpXaiRNJkGgKhXxDdHEzQZIiJjzhfMwVHhVdDsMlttcskKfVQqxxfFfHheDbBoOlKVHsfvhFxXNvVRrJiIgQqGXuWdDYyrzeEoyYkzZSsbVgGSMmXxcCFsemwWdDATtZzaomQiIqvjJoOVMORNxlROoOuHhMmTxXttuZcGRrUuSuUCLlcfAtKkTlXXhfFHyYxUxpDdPXgqiGgbBnCnNTtjoxGgEhqQHkKjUuqgYKlQLxkoOFjJBbqfEBbBJdfFLYMGgmysqWVvYyMpPmXxilLYyIDfFdOaAoRElLYOtjJToBbkOoKVviqgGQUsSueEmlEzgoXuUxSjSCcjPpTsJfFjmNnwqoRZzeEhiILlfWONPfRrXXeLbBqNBbWIDdiwyYaEnNzhvfVMHhmcyRyFqBbUlRagGHEhQsnNnNSAdEvrRrgyYGhiIehnqQdxgZzGPpjCZzhLiTMmpIidmMDPsjdlMmFsSDqQdVvoOObwjhdslImMgeCTtZJVvjfvqQXxmMglrFrdDwtThHQqWSsRnNZIkSsIFnzGIVMfFmvMFfmFwOjEeJKIFfIYyYxXlLmMGElLQXNYSSsIlmFfDCPtbAaSsdDBTpPldhHDMfFUTxXCcgcPMmHlhHDOoPpdtRrTOqBbxfFXzysSEIfvumMIbsQqgLYlLBbWxwmPpMWEKqQOokKkUqQueffyMmdDVLlnHhbDTtaALJjoOzIOoDzZfFdeACFrPpPpSGgxxbXiKkGUqQNPpdkKRrSstFnNzZfcqTaxOUuSsCcVJzMmZjvqQOVlLvTtZzoAaBbNkaTFAyoOyYOgGsPylRrLYploWwOLSfPjyjLaAAuULsXXxuUPQAaqpMbBBOPSlxyYgGsSlNXxnDFmdYTdDtyRrDuUMfTtmWwNnFxXxNnwRpeiHsSGpPCcGCcBbgdTtBboupznQgHkKDrTnwQpPzCqeDDddInRrNKPpkaDcEuUTtWwMmkKsGgSgSsGlcMkSLaGgfAaDDdfqQLgZzGlwWBwfFCQhvYyiIwCCAaerREMmsZzSISXMxgGXImrgxjJnPdDBInYBLlcCrReETNyvcCeEVmGhHhhHHgajJBbXPKXlEVvlxkDOoEedKNnHHLWhWNIiHXqQScdTGUNnuYjJyCsSsyaDkKEepZLvFmPpnNGdDggZzxXGHpYyPZzGgMwYAahTtHexYlftVvTCccCUhtpwzNnSoWwfTgcCGKkGgizMyqPkwWpSJjxHhxXZkKUwWJCcsJzZsGgwWxPVqJjLLlckkKDdKCQqgjJTtdXRaVvrimSfFscCMItlLYDpPSsmFduUUQqpPkvVHhUBThHuKIiioOWwwWpUPPppRHhrRuUoOomMzvgdDGMmdDsSIicKkuUTtlLtTuUjGfapPrEgnNkkKxXlOoljJJQPHPpQPzUkKUbgGBTvBGBbggFfqQbBMtTnUuNyYvjiSwGgJdVTjGFYyYyfrAunNUEelLYaAAaoPGzZVvgpOFfrbMmJjEDQqogNUulVvLnaHhyYnSsVrRhPVaIBmMbiHLtTomMfhEebRrKkwwQqGTJEyYbxXBwWYWwRrHhzPpIiZhDdeqBSsVvNzZQqxgtTGowazHhZxqQLlNsSGeaoOENneOzzZCqvVxXiIYyagLlbDvFfFfeEVWwcCZRuXxFexfWNnwpPPVrZPiynRrGZBiJlktTuUKLQFjZNYylLnwEeOoOMkrgGJgGAsZJjdDlLDjSsJjJlBbwpojZaAhnnNrzmMGgpPWqhZzrRHZKCcnmMNIxcwWKkCnNpXxkKnuUdDNgbBQqjcuGvVDxXdoOUEetTVvVvFVknoHnNwWGgwxXLjaAvPLlBbMtwxXGWwSijJvVQAaYhugeZxwWdDjliINnZXxHfbBjNqrfWwGgzWUuqQhimDdppPLUmaAFqcCJjeTroDdzZjOccRrCtqQnOokPDdFHxcCaAQuUoOqvSsYdDvTtVaHMmivVZzvCcVDoWQwWsSqiIwgGVvcGnEgGaAeEAaeGgQWwqJjzwlwTKLYcrLOGsHhGKkgXxUiIoCnKkDdUuUJjkNEcUUuuDdaAOGuUuUZesSEZtTzNVviIyclLCYhVTDzdDWwAnODXxAaYyfFdommiIBbfFntTGhSaAPIoIigRrBUEejRrxoOGoPWRrxCuUFxRvdqQTtXxNHuUFQlQZGJTiPbBbwEeWldPpxeyPoQHRrbcCzZjhHJusTYBpPIcJGgjvVicCioJHhzhHZjXuMeEKkQYtzXfFbBvBnvVPWfZwjJURNKksqQbTBtIiTbPAaDdHQqSssaAEeTWwdDUaAIiuzOvVVTIijmMhHiQKktTGLBqQpPHIiCcrRpTGnvqmGhccCCHjKSwWwAgGoJjlMOooZzocBBLlAZziWwHhIQqSdDbBHlLsaCEeBnNhEzTlLNDDrRdtnNTnNHnujhMmVvLtfFmSRqQqHnNJyosShHNnnNNnIWwqSJjXoOxemMTQqtvVTvVlWnNwLtquUeEbFBHoOEEeVveiSsMBUMmWwuYOPpBboARrzIicIjnvVSUUkKuuxXxXIQVsElLRmMbBnNeEhHpjJjYDpinEeSWQzPwFnDdtcpPCTtesSIiFrEBSslZyYxUuXzUCpPtTcRQqrJqQkoOImMiUEsVviSxBbXleEqQBxXbNnqeENnfFRpsZzSsBFuUMSsiGLxaAXGghHAJoAaepPEJevVayYaOoFiQqIeoyQqYwWOEfAaGgJLlTbIekhuUzXykhHYtsqTtLlgJjEoGgyYDbBbKIiAhZnzSppPNoOFfGgeXEezIiZoaAfstSsSFrxHhfFYnGgFBnNcCGgWGCinNAlKkvEeufPpmrRMFoEeMSsISbzZQqDPcavhQnZzNSVqQcKkCqOoPaAwPTtGgrRpdDqkKYoOyQlUgRrnNGifkEKBbkIUUuuSsSGIisVvSptZwWzTPBpPpTtRBbeEyYEEeNduDdvVUhfBbpPtTFBTEetzmmRsyByZzswKkmMjbJkCGgFfNDdnrRWZHRrjJxXxXiIAtPzVvoFeENESbozKVvksrJMwwsSWXxaAhHwWBbbBzMDdbFsjKkqQCFfcQqkDdVvrWmEmbEezCJQqjxTNHucrRKuCOoWwXWwyKkKkDdCKkcdDqQtZsHonEeHhzVvZSqeELwWPctEeUuqQZqsSsNaPWbztvDPpdVmgyYGZToLVUoFfOvRPKtlIxXiLoFJjEefibBIjYyJFywSsfGVmtnNxXKknLlKkDfOrFfRgVvGZGgYRBaSDdfFsxXFRLJNnFwWzzPcDhqQHSzmYVOoUIicCUuaADzknZzKbzZpclLCoORJjlpPxXxbMmuDDmyQdKjJgisEZejJEKCGecCVdDvYiIfuoPpnxXTIiuUvKxZCcCjJcpnNedDCcbBXCMmcqOoOaAkeEKejYpPaOtTcCRruagJyywVlLtpPKkfkxunAbQLxmMXJPKkHTtSQqYcylwWLYCaJLljptTiRPEWuUFbWjndDCLlFSxqbydoZvVHRCcoZzHqQKkRNnrHMBDdHhbVGgwtuJjOwsSpPgdOWNMmncIxyOgqBboRrinEjtYyCgGRSneFfJnYygGNPMmyUwuOoaxXGHsgGShttTyfFfYMPsHAaWMmTtwhWwTOHhIrcHaKtutEMmeoeEOHKLQVbGWplWpcsapmMJlLAaQvjOoqGUUaAddRrGGEeKkyYgxxXCcDdXWSNEeFfypqzedDmFFJdqHxsSYyaAyEeoJjOknyYdaHEqQxWlLxXxXyZwJbBjiIqQlLrQGPxXYBbAaKkypAioahROcAabTWOonQovVvVeEFfkRrbBspmMgfFauleKkYyCLlUubIyYTwRtgXCHvVngGFuUvzZJwuUCtUbJJjnNjYbRLbPGZbSBLlbUuObBaAwqQWSYRGFwxXKtLxXllIniIBCHmBBMmEebbPHcQqSDtTMVydDKqQDdVyYMmQqrxfUStTnBSSsJEejsokOxXvVIDdDechHUWRnkRrBDcCrRdPLlUBKmMrRGoTtWkKLMmuUhnoHhKzSKpPDdczZnbBUCmhoOmMHMkKcEiwBfFEexXcCjwzZStAaTsGKFfkWVvOsBgGbswKBjLXixMDlMmoOPvhHWPpwIgLMSuUBIqUjJsSQqlsHhuAaQSsSfOadsmlLYRvnNsSVQUJcCmymcCJtThHBvVhdOozLlRldDBxsSPtGgfSKhELKkDyYmSFKkZxIAaYyzJzjwlChTtpAahKbBqQlYuFxrrfFiIMbBgGsTkKcCFfaoWEHhnrRxQqXNIiesPpSkyLlkKQqFfFMSuUXsSyaBNiIIknJjuUNPQIiqHeEdHDXsPkaiIlLAKpSsOoSxwFfWhHMmmlRiIpLaZRraAlcCtXzRryYZSiNnCEqGNnPBvlLVgGUpeEPugGYyHxxYfUKivNnVFWxXeElLnzuUqqwNGBbhqeEQHwfPjBFydteETDuCxXcsyhUVvPpWdLaPEkpPgceFqxXRLleAaEqeVKdXxsZplLJaxXAycNaOwxnTDdtUHYIxXvVMmEazjJxXXgGsLfJAUvVjZjItkhHNYytJjAyYgGxAmwKkKuULlkpuyYUPnCcVvPuWUtTkjoOlFwWoOEQHhqeBevVlPpZgGtoOTzRrMJcBxXgyDdYGWhHmjUudrgjgRrBFNNzZndzZEeDgGKkquJKwNOjdEBbqRXxPpWwEeWwQVMmdDMVFNzwuZzFfRXxXxvVHwhjjJqDDdGTeEtzYkxDnkrnIMmFgnGrEQqepwzpPaAZSoLlOGvrrDcCyYJxDQiRhHaZHZRYyEVvppPmMmIiFJXsYypPbBSrRqQYJFYyBAaakFfHhZUqoMGXxdMmPpPFfGgJLlbBfFjENCpPWwWwpevtTgGoSsOUmMunvVNnsovVWyVvYeCdmzFefbtlLyYsLluhAhzZnCcEeJPpzUEbAMWCcTRMmYyrtsShdDQIiRQqdDQMmaJDdfvZNnzVtTFvlzYyZoOYkKlLsjJAapPoQUVvhHuquIqQiUZzfoanNXIiFNuJJjxXeEGwYTJjvVhZzDdPUqQrBbJshMjJyWwokDdycLkfFKQSPrxmcKaAkZzHUvFcjqIqQMsJcNqiKkZAaMRrmXKuPLnNycCLlTtGVpTsStytqQTYERqTtQKkPbBmuUMWiARDdbZWwzMvjPpnWwgLpOwdDfOwXttkehNNkjJhoYaMmApybBBqQbCcYEiIvVfmMNnFSAaswtTyqQXxeNCPVvkVvfZjJMmVFPDAadilLIOKMmJsYyYyvYKIblXVEexXNQEnwESjcxwWJPzbSsldDZzVNSsnkjJdDdbjTxXtsSauYytNnEOjUiNqQjiIRGgZzrJGWwgnoOnxXNhKksSrmmMVvZzTsTthTYNkXxcRrCGrCmtsSTXkKLmMlcSwJbBgFHxQuhXxyYHrRTaAuUOoGeEkVgGdBdePHhpEZrReEGwWJBSrQqvKiJjVvByXxuDdPpUowGHwzZWhsSHWrRncsSiIqQsNcFRrfWhEtgLQqMuURJijbGgsRAadCceEmMACDdZDBbSsdmCcXrSypPdUVcCKKIYMEipHaNrerXbBbBfKWhSscaJcsoOqQfFVkKyYAOoqTzZcuqnNPEVWXMmcGghHDkTJjaUucicPlLdPpDfiuJAaIiiBmdpPpDdNyYLQmocoOUhHEUBZTXvwLiICOoqQclaAHtUgGfKkYPprlmVbByhlzeLPpWwSoOAKaQFSbedIjgpEetTMxFbBZbSzNDDLMFzCrRBbxlwfpmMPeDLggDgswlLWNnopqQjJPoJulfuUsSbrRBeECcFIeEOZfnuUVqqQpbBrpKqsjfvVQUPvAWXuOseEeWwEIqcCQiNnSZoOIIJSpAWbnpHhQiHDgMuURYTwFhNnociICbBatWMwWeXxlayYoOGAyGgZBhXkKvdoUkhHaAuSsWwUXxpoWwOkfFKYylYuUFfZVSMmQWEmskKhpPqQHSpPWGYyWfMiJjQhlxXLCctFIpLZrHASsJzShlNnuyYrJGGggssnBaATeEtuUGHhvspIjJiRrHhPSGhHgKwOMmoWdRrvBbmeuXxULliUttWjJwTfFOOookbBfAaFlBAaIiIbBibMzZzTtMUoOlnNLuuUhkAakkKKQOEzvVNZzIyBdDWwsuTtcjJFRThKaTdDOoSstUMmMmtTGgXtmMSBeEbESLvVlszvIPpKkuLRXSsNkNxrGzAtmqGjLlCcwWYTRAPWuApEvVRqxtzyHhOJmtVkKiXNcCJJeEKkjmUqVtgGlNTeEBqQZBboOyYneNPVvfnNFYyLlSrEdzWwmtlxWvlLCqdwgBFXSUaBgqVvFfyYhJUIIzZbfSWfyJfFBbggGGOYmHSDhHwxkjGvyYcMcCdCNHhPpbFFfQqJyYxlLmMtLpvDflLQsuEePuQqUDbjUdDHYyIlUGgvmWhhMmiqQIZExYyCWwVvPpsaLXjJxaYBAEDypdXyVvYxZgiILlyaAFhelQPpjhwWHEGhHUuKGgtTzTjvHhXmMxWwwqKKkkUuQWHeBbtIxXWMKPTxaKhWwvKoOfFkZzuXVzldJDzZjZMchlLNnMmJjDbVbJjJjHLlhZDdYyiHMSsZgkCHqvVQsShUJvJtfFfgvVvDRrdEMPpmhOodXxVeEaIiASkRrevvgwyuUEeYWyuvVUhHRrhttmQKJDggBbjJSgGNnsYyIAavKOoYyYHhxKUukpfHGNrRnIigOuUsSaJiIByFjmVfYyFJUuIRyfxTikqpKpzAWRihDbBEedvgvaseZNAaoVoDWtbRMnWxXpYaXOjtTbBJyRrVjTtmiIIpVZzeIiEsBtAoZBbhonFDHEeXoOgnFxjWGAqHhKbBkKkjJqlXWNGiIZQtTcvJXxlLUJkSxYwWrRRJhHNdEMwWmIxQqqUnpPQhyFfYsSjpvVoHeEhOPpneEaarmJXtrxEjptAaTfEUHrRqQlZWEewzRtnzISYKkyAarjzeEQqmhHZrHvIsFiIfGPhnNvHmMZUxaxyaOwbYKnTqQYrRhJwWCcYDdoOhQMVvOoLLPSzXxvxBbXVrRnxZPpCsPYOqQGzMcqVzSshNxOmebXxuUMZbphGgDuUdZIizRraMCIRrPpicWwCnZdnNDHhkigGbBmtJCrhADhMoWuUweZzRrkKxXokaAZeEqQPRCckAadSsDKLlFfvrUnSsREeDddZzQqHRZLlEgaAZJZWAYyaNhiINaAWNnBbvLCTVcYykxfMnxJrQiISsqRjmXxUbCkCcVKoOkmkispPSWQwJjbbHGgIXxHhnWwXxtTCCtDdzZYyTqUMmolTtLIiXNIimesujnPpNjfgjJBGgWwNWCclaAhWwsSktTohKBKKRhEekYlmMIuZzPdwdDmtTyYRrYYybBTSsflxFfJfUaNnKGyeTbDGFikKAKMWdWwNWQYyRQjjjlwKkVTAJaLOdkKDTFpUaRkoHhJCdDcyeZCCcWONTNntnytBToOvuTDIECRmXxMXzPlLptHnvjpJjWwWwaAtrRAjgkeElIApJujfCSTqBbPpzZlnNLkRnBKaAkZzbpZzPHgtTGQoOqByaMqwWxXQsEPCssvkuUHAqIQpTtXkKRrxVvhBXzKvNjWBbwJnvVVOeEKhxKApTUSajlLcAiIahUiWWwkKVzQpPKkmMfFXHzOwFCvVqulTXwWpaAKVKkqkKzEerPpILdhUnFUdDRAaALBJaQBlLnOSsrcCkeoixHsssSSTpsJNRrcHhBrJDdjRbBkKhiIDduUzybZTSrmMCDpRXxJjaZzArxOelAawvVNFUSqQeHxXSgzZzDdadbZAJagnmGgrsSgMUuchQCaqZjplCXxRrJkoFYYUWlpiPNcUuapoONWwxlGigJjGIaAQqbNfweKEKSmMRrVPpuFZdJhEdrTEyhwiRAauUyGgYvNxMmmMXSrWhHskIUyzZycYyCYdDsSYurRiKMmUuSwuURsvHhVnVrtTIWHYrRetRDeHqQjYyDIizfAaUPpvcCskekCcaAuUjJEyYWFfFNnZznBgVvLXWwnoOPACnpIPLwuyyfaZNnrRzAOKjoOcLPJzcCwWQAcqHCmGRIBbiCrMmRcgGMNGAjfFazZzBDAZYyGshEqQsufnWLEoXkKPdcmMRstZzzBYZHbCnjSPtaAShXAaIOEKRoNbqAjbCclarpmZzMoOPuNkKxXnfNuHlLDleqQEiRZkKQvxDdXkXxPlLxtLUQcfWoZKkNnhxSsKkqZvPpwIuHbJjBDLlnNdAsSaCJAakKQqAsbBuJMmjtPakQqXYeEJjyHDQsSqdkotTkZxbHvVTQwWqtPqiQahKVSScpeSIiGgPpEemAYbhJjNjJrCcjJKQtsqQPpcFJEeUcCjzpPZBbPawWiLAalLKGJaTPJVNhTZAaxrceiXxdtUVtbThHYnNoGgwczEYjOKrAuyYPDdftjJoljJAjatvWLJJDdJqrqwnmMmMDwmkaIfPpgUudBtEYxXoOgQqWwkAuFjDsSdXIAaitTrRLFbBtGgyMWFaAUufAaDxXpUiLyKHrkkYybkfFdDRrHOKHvVLlLcOoCwnqXxQbaAMmFfmMGiIvVFJkKjfFJJUSEMnxOuQccWIiwNiDXxdhBBWqwbBIAaKhHMDdvKYeEycBuJjMEeXNmdDFXXxKZzZyYPpDdzFNnfCvtclPpVwZzmUuMnHnwzjpPzGePGgrRpzroOhDWwrNuRVBbrplLzKOqQEDtTdOVzZvmHdaHRcjTgPpGMIKcCzNcmATtHPBzrRmBEMoXnHZiIvQCmZgoyptTBbiISczXNhHZspllmqHywWjsSNnHyQqtVvtTNkyBUuWoAYwWXAXuzhVHpgvHhVUuSCciVhRzDdMZJBbMtTlLmRsSsiZNTrrRLcChueFPqsQqSLlQGgJeXRtTGgTxjMRAAvHhVNTtSszZPJHqNuQXieXxMAcqQCamDnjgGrQqyXsKjVvunNjVCqzgnwzZxOoIvViLQeEmPpOofHhkKFMQdDqQagwJXfNGxUuhdfNOjJHiIzOaTbSPpvPpPJgGjiMIZziCwbkKBWcJvuLlUecloOLmMCEYoJPOPpofFpQqjxAyPsSwNmrCcYyBUuTwdOvZzOnzESnNAVLlGVrRHIrwaZPQqkPQKItXFYsSrDdijnNYyvbBMJUujJfYfyYFbBbjAXUuxooOhFJjPXykVvVWwvhHViGGdjkwWJjwoOWqMTTHmMOXxNnoYGeTtEVVLlZzEKsSsaAkKvDHbBUuIieVnNfvVFtiKkITGFZzqQvVDeHhEvVdTjcCVjSsucKGzmhIzkKBbBJjvBWwxXdwWrRpKkPXxRrgGAaHCmzPpsSeEKkJdjaASLlsDLZvxUVHkDdjJAXXxtPGgppkmwowWqQOibiIBAjJTtaInNikKTEhXxVJtZaAkguUTteJqHhLYyEHfzZYDdyztTZrRYGzFfnNyYDPYdeLoOlTMEemtmMKuUkabyAlASYycXezVvnNHHwMVuLihuJBdpUWwSxaAgdDGuUXqFdhVPlTXjfBYyjJPpncDmCVgJKXWdKkshMBbyoNnPpjYFwsFfFBSsiiujyYmPpMHpPQUuKkGbAusxfmMvVbSsGWDGgEeQcVwXLTMSsmMWsSHFuwWULlfhwZfFDeRsLMmlYEeypnENyYzbtnLHhThHPpvQuMiIjclLCnxIvBbTMjqRrQoUucCYZTXQqQrePaUwpOoartyIiJZzgQMTaYyZgRXnKGwWgnxrSsRrlUiVmMtTZexXEpPoJxXUujOEerResTxIiuqQAkHtrfCUSXxbYuCcUinZeoaXxAqCKkKkaAcgLlsSGzPpZKHuUoOmZmOoqQLxXuUKTjJuILlDdEMRrFfVDkVgbNnoONaASSjRULHQqsZjaMmUuhNJjnRzlPifTHqvVJSsjImFwXxgwMaAecCwqevtThHVEkKIsSisvzyLPmzZMfXfEeiIkEeKFxFKuODVxHbzYagRrpPALPpMmEcOHfFhoCmwIXxzeVJjvEZiBHhbTAOQBbqHfWtyrmGHhkLlKsSdhIqVAavYynNWwPNBXxfqQFwpPaPsjSVvsiDdwUuWizoUxwaFfVpuqpPFJSQkPRPQvNFzoiYyLUCiIcGgjOjJDjJWwdIinNORrwWSGdGLlGldVvJjEFWLXclLZfmqQKksSjjJJWwlDdHhJjdTJjthHfFYyOocCdnZjJsBzfBbXmZzSsPGJiDEBsfqYaFfAyAkahGQqaIiAaXxAgHoOoOEevVsNnZzuCcUlEWwZcfFHhCLHYeErRvUuMLRyFuTAahMmWVbaABdmMDxEetzbueuxXtTEeCOCcMqlnEePDMbnSsNIvzZVjqQUIUuFpClLICkKAtKcCQqduUCxxCcOoTtXWwwhcCHvepbDdBQUCzZtoIiOQCcapPvjJSCjACHwkFxRERnAhPIBbBGgbemyikkxXvcCuDxXYOQqKkosRxMhHzDdcfFaDuUrSvVBJIjrmlGTeHwCnSqMmQCNwAahgSsWOsSWQqwzZvVYoObIkVRsbjgzDNeEnYybDnNLlvJjkKTpPtKyYgtUoOqXhfGjWsCxhHMcRgBbKnRkKrrRyMmtHStMgGRsSHIqQuJokKeTbBUAXxJBDMmKkKvLthHTBZUplWwkKLlLAaPmEeMupjXTtQqCJstTeWXxNeqrRniIvZzWwVjJvxoOLumMlLUBikyVVvnNjKkJSsSYyjMmkopFffvzWiIwkKFKpcnEYWezzZZPdHhUuDyOHKyYnZznHoOEKTTxWoFWoPtBbTDdlGqQNJVaBbAmcCBOoraIwprewWPvgYlpUkxViIvzIMdDmBxmMXbQnBbCjShHmiQJCmMfqjPpvVJFfaAQHhVuhtTCMXRpsjJXxqlCzZYKOYmHSsSbBjRupHbBoOtyWgjBbUnfxAOFQqOSolLOyzvdDVZxXwWYykKLVEelLjAqrxXqHIieElLwmqQayYLlBeuZUxXuPJjxXpjEeNsSHhcCHabBHUSWwTBFEfZVvMDcvVQqWwEwOSmMNzZKkVENnaATtxXjJkzZTtJjJjKPcneoOpGguUGNngyYTtDtTYygVvbBmOQuzFfsSKAdTtDbfjyroORxjfjJMPeXIiqQsSxOoYkKyrzhXxzAbBHVvhSsrDdIqdXjdRRcCVgtTSssNnWhHPRgNGfiNRKNdzZXKyZRsSrgiIdDMtTmdQGgJHWhrAaMmUWZnfvmvqrQNWwneDJonWkjFfUQlLmLlMaArRnfbGJtTfFGRDJMwVvCcbCjmLEfFbQFfSsqndXxDNfLKkJKuwUpNQqmMsRrSWMaXaTnKTiJRrzJqQlgGXxLuajFlScKdDkCxQeEqZAeiyhuNsSXWouUUuBbAAqHhQanCSsxXYjPzSDkvEQrRrQfECnNGKepAlDwuHYLlAavVJjSUYfXsSxbJpFWgiInWQQZNwfqQyYeEIkuFyXXhHhbpgPpQWwecTtnNIdDsVvXxCcxTLaAKkuUHhyYBbzAlPrLMNnKkyYnNMiImdhDhFfpKLliZznbAYxEeuJjUsmfYKUuwOAdDtSHhmRfFRhHXfUyyYQVvqoOLIiGglLkXdSsmMSqQsDxaALlHXxYyoOJjrnoONRQqMNnmPHcLWJZxXjZYOoMmyiXzfsMKkdleHksFTpXFfbhHLrZDHbbBjpqQYyPJjMYMjuAaqMmtTryMSDAoFhjJHsCcZzqUSLpPmFxtTXfMKkCcuQibsmlGitTcCVRrpHhLdmXIxlJbtTnNkuUWrIjJiRSiISoeEeEwwWgSsWDdpPJbWIegGucCNCcCksTtZkRruUONHJjlwOgkbupPpbSsKNrwuPpCEdnNINnaAlLiivVoKObNDdEesuFfFdDXRhHvHhhdDHkTPptRrrRYKkvmdKJjkIvVisChpMhcqQbNiNnLTkWfOogrysoUusBpPzgpPpBEelPpmMrByZzyYBCcuXxTcgNnGWOojUdDVvuVfNWwhcxyYwWGUurRTrWtiBcbyYBELUZzAGvVCcPaeEAQqSKQqiIOqNwyYHhtjJBColLrHAOIaEegqRofFOLIilWzYwXenNhASqQsDNKrRXxsSGgiIYXhQDjffeEMEZQPYfFnFYyfPpeEFfswhHgMXqQxmuUjJqQGgDDueEuWwgQJVvvVVqjPASCPiIKkwwWLPwgBNYynjJvqDdlkhTUTNnMmkAhCRiotSYypmMdDmlLyJjFYTHbfFBhguUADdPyBbYEepYyJGRrgjUDdWuYgaAGpjrRJkhHKjENsrcwWTLlzZJxXDdJmMjeNIlLOjJQEeGoYXiFfCwoDGWWwoUTWIivKkmheEhOrhzOiIDHhYBQYyXkKsfNncxXNJwBHhfweprRqQIirlLIPAyPpshKkpjlqBarRNMmeEUXeEKXxFTvLlWYYLEeljGRrKkTtAzZUMRkKrmoAyJEYyKkmMoQxEHRrhPzXkJjzZKkVsSZztNOSsUFyqQjJEgCiIcckIizeSoOIGOokDqYMTtddHdDhUBvVXLrkKPXxRrxXBkNnNKZdfXxFWwuvVvEeyMrRZsdFfCpZRrZfuUhHjlrfpPApPbryznVvNWwoFdBbNTMvgFQPpLPplqWsSYfOTRrGglLkqQprVuvlOvVYyKKkgGktdDzyvVYMRrBbTZBwpAneEKkJWwjSQzAaTHhWwEeCpSslQsNsSOhSzTSsVyYhnNHyYvYFftTxcqQUkrRCUhntXBbUunNcZBMeEeMwRKkeEFxXfKJStTqQfBmvVZEeaAkKbBsSsSWtTCcmjRTtdNeEnDlLSSsIiZOBseGgnmMfOZBtTwWbpTNnaNnrRhzwumMUcKjBJtTWSIiYbRrYSrMVvPpMiIZTIitbeSvVsEHDnUufFWgGweqQrPRrbwWgUusnNHhiFaAfoOfDdFeKFWwFfqQsSIuxaAXLWpQdRrDvsqHVACEepdBsimOBbRrUNnVKkLBbaIcylLYgwbfNyXHYyhRfXxsTSYpPyFgOocCGBbOxEnPYysZNzyYzFfZHakBfHhNWqQNnwnMmFdOeTtGQSTyhHbByYDdKYxRrZHKkpPKEAVvaiBtjAAXxECcEejOjAaalHhgNMmWwnIgGmHhJjftTbSgGPrXDdxQLsISeujJKjuLHhtTfFOobeRfFVvfjJlLEcWwKkBbVvCTHhcCNfWpZqwsEQqyYeNIqQPdyaOoAJQqyxXYgGzZPreSvqiuUvVEesNJiCZJjaySsbFfLlMmmIZzwbBWLlsShbVvBbPpfBfFQEsKkQiONnfFYjhFfQXNnxHhrsFfQBbHhqzlLnNZMTlHVvJUNhtTKkdmLlMntEUueZeHbcdDAShsCcYycCvUfFuVabPpbCOwWuPpUOBbXxmlLLFfnNjJMmOaWskJgMfFNYynQVzZYRGgrRyZzYryNgHbBhhHtsSPhIiTsSVvtbCclgtTqIJtYyvoZtAaShpeEeEweEWtrRYyBSnrwWuWlLzFXxtTcCwpNBbbVxZTFXxFffyqmUxEkKeOIcCIOuUqlLQozZCYyibhHhHytoOSUBBBKkbbhKkXjJxVvquUOpYuUVEzBbSsZevEXDLBvVWwpZzItjghHzqLKkqfWwhMmnLlDVrXiIffFcXQqwpOQQFfqqgXCcnNAaJCcubAEeaCczZGcCOiuUDdgGpsHxXzZgNMMlvVIiLZzNaZdmMtaAvHVvnrOoRzgopPrRCentdDTKurfFNnRNcvVxXOuSgxXolaARCybBlktWLzZWiQqIZMmNkfEeFHhkKSsKgCRraAlLvVbJjBoOOdtTvzZVILlhAyVhHQSsqXhDdfJjpKWwNBbTKFfkcFfjvsSTtVJCCoJMmCcrRORtEQfMulPMIHwZbBFVDdvEeIiRQnJFKkhuUkKzSsLZLlzXxyYJQqwWXzElLGUHynNqIsgjDqQlLdXFfxcSsHRYyRrnuUNrhCtTJWTmFfVmWwMvpPwWpmNnMVJlWbBhONFfKvfughHWwUCJGEePeEXQwWqikxXzhfFJjHaAQwmbBMZBbRNHSsVvzJOPwWWLdzYySapPjZzRKmoWFfnQqNzJrRfqjrRgGdDrRIbeEzgNSsYIpjJvVzRvpFfFXEHhfUrzdBGAQckKZLgGloRrzFfuUZMojJOLlmAhHEgBbUunXKkPpAWOBbXnbQEHWwyYyeaAFfjtgYfFyWQqIiWBHnNFOlCchzZAbBvxXpHcCvURruNnNbBAoOjJGOdriIRwvqQZzVHhWpSscCPeBqQRyaRgJtveEFqynNCWwcYQfDjWsmMsSuNnUIJiIPpOoHhvVVxXmFfGbVtvVuuZpAaqhpqjLkKEAaeLKusSNgGnUGeVvRGgAlLFgJIiIisSCVvvVzZVZPNnpOTtlLruUUuuPIIikUbBtbuKfFunEeNDfluULLlQqMdyTRArxDXxGlQvXxKkpdDDdXSOkKojSjtTKkDduzXsPKzZpQYgGAamZIEeRrteEaAFYyOsZWVZWeEwzvVvcCPTHYyuFLyXuyYBbUHhWwEDdyWmIiTthvBoOQQqqYytTbVMiIfVlOozcCPqQkCcKdAYlLSuUciIgpPRrDdtyeTtEUbBuYDCsxXxtThuDdUhHYySFfsaAAanwHwQRrqjJcClNnhhsScCaIiAzZGgXLkKeLGgxkpxAQqMKkxXEeYuUBbntvYyVbSLlsiIyNibpSsNXNnGRMimxsiAaccIiWVaAHqcdDjJWbwGgWFDgGcCdLlSsdFAVbBvlsKmCFfLDdiIRrTtHheCdAeEdDiOodDTtMmUlLuEQccCZqWNtRZuUzfFOodyYJjhuUGqvViINfFZbjJBeEqQPnNUOOXxolLDgqQhINnEcCcCFfPrHhwxhHXESfCgDdGcFsjJeWWSsXfMdeEdDLXLspotTZzcCbkKeEmxSljUuJawYyWlrPEepvVRJOPqTtQpolGgLYmMJpFoyKMmYykYYaftEeAhvfFwWKAakVHKnlmMLoXAtdDQBBbFfbCuMmeDdEUSsnNTDrRnMmETteFfugSYysrRLlLHhlyYIotTOxnNYyBAaXtTxXXsREAaefcaEAaxXIiiaAZRbBrCfFZzcgGldBNfFLlvyYXxYeEFFEedDXwylGSBinNCcJIiCcUqOoQlLVvVFieYTtZvrpPBbuGgSsUiInNRVQZXxzQqoLhpqQCGthHumLpcdMLvVqvVQisygGmMnxWwqeWaAqQIiwgmMOdDoyicEeMmCiSsrRkUuovenNEVEeBbihZzvmMVHIrRRbBrWfjJFfigZNbiIBJNnjflLmMgfFtYyEeTGrRyYiKizfRLGVFTMmiItMmzcEGeEnNiLSGgDZzYyRrHUuAahHJWBobbBKkBFffLDZzJStTUcCpPxXutZGgLKxnNXkGglmMzdDZzgGWfFiVvIwBnNYyeEbIlHcWfFwJXDNHvVEHRJjVeDauChHcIiUqHehArLRruQfYrSKksrRYCvFSsPpCfFcgGGgVHZeASsWwnQhHlExaAxFpqQWmMwnCaAcmMEeoWwwFHEmMerOQneENBbKkWMStJPpsJsOGZqQVveiILoOTtZzMmOosfFBbkAaKSuUMrRqQGgIrvVIoOiRyerwfFQVPpvSlDjbjJyYeFjJQwzZhHIiWYysSfKXxXMmIYyiuUSsmLiIlMlaAqxXLgGkykKEeGzxXZEeQcvVlLCiIAaRxXrJKketTXyYOJcNxXIQGueExSsLaFsmMgCBbzUbBOoTUorLVoOYyvXnrSsAaMESfsvBKOZqQRwUxNRZzrnjnoOPpSsAIiaIsSizZfxAaXmMHVIiFShZsSzvkLdEFXyGgYiNnyYIugGUXPpvoOrYfFyRFSWwCTTLmSvOoqFfQvIiNMmnWmFAawqQWHqBbAawWZOoejniIrqQIAxSIisyIiYVKkCQgGcCqcSHOVNnRIiGgzsxXtkcfTeGoOcDhHvVdVSsFyYfUfFIzZvVIivWweYzhHZpaAPSYPpyleXxEqiIxxXllLFIiiwJNxXEUYyScCsoXxLlOSsBLIsWlRrGRcCrEeiBbkzpfRrFBAXgGesTtIPjJXbBtTxxXBGEumMBbUbSUBNnvVtuHhbBUOyYYSetWPgtTbkKkLMmLlyYlRwWrPpKnDNRaAEeMtOFRrfDFQqjlDGykRrCjJchCcXAaxiDdvqCcQVHhdKkKksiISDrRdJNfjJLUuucCQqqQcdCwzZURsvViIOoSjVGgvJPyYyQaAviIGgWwHhLHLRGgDEVyiIYmAQqwqhHtrAEjnOWmgwCylEFfEevRdGjJKnNfFkhHvVfFgFwHhHXfFCcnzZNYKkqGmMSLKklNnWwsJqQjUkCRrRIiFNbtTnNFfvIBrGYycHhooOmPSspffwCbIirRBciMQtTqXZzSHoORCbSsVvyYyYBqQvVNTtAiIyYaRriICDdkwmlbBLrqkZzxXiuGgUIKQzEeCMEeOoEeulzZgGJjJjfFkgtKSydTmXxlLdsSdxXFnAuUmDCPHhpgGfiIikfFKiIoJjwWalwbBYyWmpSssuxylCGbzdhHXxBinyYcWwpLlCBbciIViLWwlLWeEwpPCQRrjJwWTtCdcCGzSBSWtTjJzjJZonNOIitjJTWlVtTrWaAJjwRknuUiINeMjCHGtbUwQvSsalRgLHQmqQFCbffFFrbBNnMrQLlvQqVxVWXeExdaAlcBfFaYwWFZOoRGgMlBbriiKjTtJHvVhkzvVIOXhmvViIwNIinrRWMgGoRYyvzHdsShHpinAQyYmvzPpZveErRlpPiytSsTYLMmoiIOIyGgzZVbxGkKSVvMLlvbGCckKBFfrRuUOoTdDvVNrskLpPlUroFfORXeEQfmpPCNwJdDYywmMFoOHSpcfCcpLfFJtTLmMljRmMJJLvWDvUuTLIbBNSoOAaGQhoObBseEOZzypPrSsRYHyYYyhvVMYoOGpjJkgpDdvVoOcOoCPjTtJeNnAWWwwsIevVEiIhXKkSsgGxEDRrzZXWwLlxoORpYMRaBbuaAlLUAJjKkCcfdfFOWwfdHLeOnNoOqOobNAanfFSrpUCEwPpWhhHvSAbGgwWRgkwWfFAaVveETtZzSsfFKzZWyPpQqtTeEuKuUkOoUunGgrSthdDvVHXWwxPwWpHhyHhEeqdvcCVIUiFOoPWwhpPzwEeWNjILlzkKXxWtuUezmEeqQCqQcDdEnTbsCDduUcxXMYoeEotTOnlCcLDCcdDrcgOoRrGgGjCFfyYqTtkWwRrKYyQFfqtouUOYXxyeYlTtqQBbdBbiIJzZjJhHPrNQomMHiIhPVvZcpPAaCbByYQqFfYlLyGgzsSQqWwXdggNOqJblfUuSsIizkKBbjTCQByFmznNLyYVvlfNzNnQqZJjpPxXaxtTsStzWSRrPsuXBKkmqxEeytTJjCcYJmpPGgWyNnqQaIWwpAasSUuBpxXRmMAFzLlZlLeAaJKPpkHImMjPeEzZlLaAxXpAPnNptTapPJLluGgwWYyUxnoBbeRhHrKmLtTmEeZpPRKyXxQyNnYqjPpDrDMAamGgGfFgOzZoLlLlfFekKlsSxfFXjhHzEnwWBbVvAmMQqeECpPlLYzgGDFnNIiBGgabBiIAyYdPpBbVCcSYUMmhYyiLLlSqQTIhDdOoeEJjmXIipynNYPxnNwSylWeOoiQZzLlXKkbCcJjEeBpPhHMVbcMmCBxXvJYeEQFzZCDdpTNJsuUdjJNtbBfDdEehHGuUgyuhHlLUlsShHiILxLloOVGgMQHtPpeEekKbBEBblzWwwDdWroOFhBYmTPssSSiAaoOBmOwZrDLldCnNlLiIPdxtTXDIirZMYyRrmQVMsMmQEeEiNFpefjxXoFNngVrRHhxXbgIMXHhxAamNoZzycKkdDCFfAaqSwAnMmgNrpiyeEYIPAaxpDdzZRQqrTTttyBbaEeyRrYiIMmSsAIiOTRrQbBqGEeXvYyVmwWMWssKkGgSStTkZzlAnjnPeEAhiIaUuAlLdDbeZFNntTFfQtTNgGHCcsSZyHhiWDiIhHAaAaRqGghHVkKoVIkeEHhLlKpKFLlpipaMaPpoTtuUOZHhzTmGgKFCoOcwWNmGgvKsuUkAAaOFfcCofvDkKEleNnyXrEeCSscdDRoCNmMgOoGxcnNXxCPKkJbBlXrEeaAHjJWRthHTKiIKkATBbusSRrlLxXEdLbDdRjoOJLpfFguUOoaKnNqMaAQqahbBfFnNlLeaAxXOYjCcJHvUTbBOwkKJNcYxMmOyYiOoZzmiEGgeQqFGrRvVeyhHXxYrcnAUuVUWwuFnmMNtTfydqQDJUujKkrRIgSAoOJOoFdBbcQUHUuNSsOQZwWzawWAlJjHhWwPppPTtLGgwWDsLNncIDdijEdXasfQqIUnNbcwWEeCtbZlLzLlLlUxtnAaqQjJNwooSrRQsYqQUuUAarRxXucCZkKzyRfRrjkxZzXExuUrSUqJjTtYyXPptTZzVEybBYeeEveNnUDPJvvbBVtyYfFwZzhHpPWwTBkKHrRsfFuUIrRRWNgAUuaGhPgPpmOAaoGVUrRzWOowiIJjQNyLlpppqmMwWHvVhQVlLqXGZLwWaGeOzsSZoqQtTbBSBfIUembgGBIAaiTtJuUlZziILlAvljJtmclWqNnQGVEeXxvzZfFlLZzvpPdDCnZzCchCPpqXwzZWojZleaANnJKksFpMoIlLRPpDirRMmbmjJoOjoVvgGebBEYyoOjQqjJjIiZnNlLNliShHnsSSsqQGgSsrYyVmkKLlMTtzZvVsSiIHhoovVqQOOUDdHBBDdaAbbErRespfcCBhrRHKxXveEeEwWVJQgGqDdmTtDdMfFtTokKOsTCghcCtTyMJjmYnNcCDjJedDIGyYgriwNMVvSsLBMmbrVjofFMVNnvclLOoHhXxKedWlLiIwxTkKtXiISspmwWMPpCcxZzjJpzoqQOIyYXxUbeEYEhDdHjJzLlOobvVRrBKmNMmnfFuURMmrggGXxdDqFfUDKkYyeovVtQqHshAaKHTthkyQZzqWwYrYUUuMmwfFWuyYmTtwEQeEeWgGgpPGtTpPAawEqHFrGgRfTTDdmMVvtlgcCGKkLTylLIiNnkKjCvVcWwqjJmsSMWwmPsmvVXDdoOkjJjIihHJtsSAaTKoOkqQXRgGIXoLlemMEygGzBbZYMMQvViIouUuUldDjuMmUJPnNHhrCccEQQuUZzJJSWaAwsHhrsbkmoOMIixPCfFtTcXxxWwAaBVCBvnNiqwWfFTtXxQgBmMtTbTKtTBsagfFZzGwWBbYkKCjJOozZxXkVvLAagGyxXpmixKZzSqYCVvneEHCcYyOokiqQIYfEeFefAVviWJjwXTPptOEnlLNrROobBczZsSCtTeeeEoOhVvHDbBdQqTaxXDdICKlcCaActFWlLFInNHhUCcJjuXFsvVSSbBCsNMxFQkKtFcCZGgmXuURiIblHCQqoAsSnNaaAGgkBbKNcNnLpkKPuPgGqYyQIivnNVEqQebBCQOZzofFoCcZNEtTNnBXsSSpwWXgMOoWvVQqxXQyYqflSsLFfFfGgjJTtigGWwyYJZzGSsBbxXFIQqoOhgGnbQhQqqQHqBnfPDoAaOYcCuoOWwValxMdDdSZnNxIirMKzZKklLSsOolXlELFtgtAFewWfFxtTGYyXxioeTtZuUzjeooBHOoWXFqQCnNcBqcCQZoQiIlDGoEzAagUnoOEPMmdDsSJjzZpAGgBbmMuQNnqHfQqFQqjNBRrbtTTtLlJjKkXPoAasSXSnNAVHhvJjQpPwWfFEevVqaeoMmdtTbRDdHhCcGDdgeEDdrRXiHTkKtMmoOWwAaFfdFfDvaAsoMmzZWwIixXtsSTGeyYnNnNgGlLwWEUugSDuUDxhHXgGTCfFIUEGgiINHhncyGgwWkKYKkSxwpiIZCMmPpAaZzWhHhBZUuhdGaAguZzHhUGgDBbZbvOosSAlKzZZfELqQuUqkeENncifFRSsqQMmrlLYRmMtTDdolLeEYtThBbJjHGSiZqQEKPpdDkIKxpxwqhHQhYyWwIiGkxmMpPjJXNnKKkkqQKCcgKkCuoLvTeEnNCcNLlPpYygGwnUvXxVHjYyQqAaNMmDHzMmZMmhISsZzTbBEezZtXeEHhxvxoOXqtTQMdwWDGgrRqQUuVvjRfWwYeKkElLyYCcnNueEMyPmuYyUpczVvzxdDXZwWogGgGSbBXuUuUgLlHhmMKktNhwWHIXxjJiItTBpIVDptTmyYlLSsMCcDiHDdhIfSsUuvVCcsfFSzArRaZHjJmMhkKFhHPpyTtYdCpOoiaGgAfFZCcQGOoCczqQWwZsxFfOoeEqYFgNnfxdAaDdfmMBKkVvbFkIirRGgDdsVjJvcLJSsgGYjuUasSZyYEMmMJlLjGgSVvNnstzZTyYmrRlTtAjsSlLdmsSMTtDZzJSscCpJjtkKTYAayPdSTKlLfFsRhunNUHdPpGgDfOoswyYWTFfTtCTtcIyZzYipmMnNUqfFCAaDdzQqyYXBbNnxeERUuCcrUeEPpxdDXgGSAapPSuAaUsDdsWwYyuZHOqVbBrREerKkdDeEPphxXHBbCcYyvVqQOKbBkJjoupbBPPxCvLyYliIVECcepXxPsSWbBMcCcOopIiWXiIxwLlpxXPQxRDdvjMfFriIDdeECcjCrOoQOxzZXZzoVZznNoOvsSPpLlmukKUaARrXwCodcyYCWAacCYRrMmZzmkdDasSAcYydDCuUKNnkKDpPhHdMmgGACcaMiImwYDdUdTtDPfFAaLVvgGlqQqZzTtwuUNnNyTUuOCMgGmCzFGoWweEOyYojJOUumDfsScGwWWwjJmbbBfWlLZkKzbBlVvLVvrVvRlTvmcasXxSUzZzqIjJiUPQLdMMdDmRrRJhHhHBbXOUuohvVHJjSjJytLlTxskKYybIfrRmMFiPDrbBCrVvVvRKkcMvVOzLlqQZomRXnRrvVVvlwxXWFfKgGTmvxXVkKQDMlLqQqqQUuczzuxhHXlLZSsmxmaETeqzZQESaNnAcCKkRrlJjrIAailXxLwuESlLhHTtHDBqumMUceECBuZOozUsSvRrHRPKwkKnNKWwSPpOoPpuxIUuGZqQzDdpPQqrpPRJDuLlCAauUcLlXFSAaUJjZzCswWBzZcCbWwVvSBYGIOyYvVoYyxXAwWtTfPpMfFEIWwiUuotnNBbHhTtGgTpPdDODdGgbVQIPeUuiITtEpiIBbfqHhQFHhNnslLSWwLjKkyHCrEecqDnNyXxYYyoCceEqYDyayIisSoOtDBbwWoOSpPYmAaGeEgMiFfcCRrITXCcxvVSbORFJXtTxIieEjYOTcCILliugGalpCcPJqLAaYZzpYFwWeEtiIoJVvjmMoOzZoOoBbLoOyyYYdDldAaYyRrZUuqxXGgFBvnNBSsDsHEeLPplzQXxdDqpJkSsKzSwtTWWwFfsYyoxXsOsXxdiIDxXzZWrgiIGQPpyYqQqQCJjcQqBQqbqNnUumMJjtsjJzZkKYyOoSOotTvVDEfFEeLoOVvJieAavzZfHhSssSOppPPGgLlSuUilFffFZpPsQqSxHhNnXMyCcEeYanNAlLEdaGgGoONnFfgGliIAnNaLyYDIiAaIyYQUuEbBeGTPptdDrwWRHhXxxTtXubThTwWcCBKkbgGNntgGPpVvzQqiIDdPiIpTtSQqIiWwOxSsXogoOGofFIiOzyCfpMbBmUiuaAVVGgVaAxxXXhHhjAtTTAaTWcCwyjbBJNDdQqenCcPpyCXWAamMAUoOfQcCwWqFuvwlyYkKLWVRrdfFhHBbviIgELlLlTtSbqGxXgQBnWwohwYyOowhHWsSvVsSahDdOoDwGgrMxXmLlSsReEQqSsJGgQoOeQqOtToGSsgnNyHhkuesJNvyYzpIiPmdDMswdDwrtMmTRVvxxaZzPpfBZTtEehBbHKkzAmuUMFfFuUvVfahcCEeHPPppYymMbGGgNXyYyLlSVvuQqUsoOBbUugDdIymMAIiaAPpaBUubTPVWTNnkOsvVSbBMmseESJQqtTCcjkaANXFzZfKkeExKktTXxXaAItTsSxXVMVzZmyZzYrRxAhHgGqhHrRdRKjJkrzLlWEodWyYEvVWlLaEedFfQyLlYqDAORroMMNkKnwKkCcyHNTtnhLkKdDvMmKTJCcjQVvmtTlMHIihvZUubByYAwWWwaeUuNxiYyeyTDdtuwWshQJjqfFnSsHhPYynRjVvQKkUuyLtTlrCiaQrFekLlctTFuHSsgGvyYkKVPpjwWBlWEQqBbseELiVAaiIDdYdRoOrzZDmMkKzuUsSSsoOUukLOqQvVPXxkgGKJjlOoLACFcCTKXxktQSsTXxtqjJVFfCcmLWwnudZzDUNdmOoMScVvCEesDTtemMAawWpPNnrRaQqACIiAaJqQjqwfFiIvgGyvvVwbBNgmnKSsgpPpPmbgGBKsTJhchDdHuNoOsSnUqTphHRrQrRqvTZztWwRjJZzPpruUGxShHldDLGgsTmcoOxXwIKkibBsLlnJjNMsFZpPsZzDlLdfWwdoODhHFnNfkXxKKpPtGeWwjeEtTEeiIRrkKJEUCOSIeEWwiEeFgGKkFuNFfKLlMmXkOoWhHExXhHpPfVvWwFelGkGgUuIixehHKmMOWweifFIDUudEwTLAaBbcCNnlDdaASfFUWwuoOsYKkyrkwAaRrbqMxXoOJBbjmElLevpjJaAvVXNnosSOqNuUnQsgUAMmsxXQOoKetUuTZpPZxXzfYesrRLpPJjlSEmMejqQJjJDdjMTAajuQvVqwWmTtyYMsSgcCGUyYuEergYaxXFflAGgasSKgGkQqdDLGglQlLqtlJfFfdYyDJcCWwjTdDtFjOySsCEqtTQeFfeEKkDvVftnNtAuxXUaTJjQAuUoOTFjJdLlNDdnDRrZzImMsSZnTPptsTEebaLldDQqoFmErBbQqbfvVFBZsStiKkKqshkKEegkKrCmZzMcTrRtQqmKDdcFfCaAzZkKbrySsEKkeXPpPqTxXYydmvADdJjFfJjYyctiIVNnBbNnMmhHnmMKdDkMjnUBbuNJRrJjmNnNvPpOoOoVRrEVsStBbXRabiIfVvFhZNnzxRyYJjVvGgNnOpRtTnZVvoFfUDduoOkDyYDddKVkBqQJJjjzZOFfpPobDdGsPpjLlkKUuJhHfFBJjMmbXBbXxGgzZpVvVMTAXxahNNnnfYyFvvzoAatQqTOqRrjQVvfFbBsSHhTyVvYmQqLlMlLpILlXuUsSDmoYWHhOOMmooiDdIwWkwWwWKGghHwhvVkKvtTVbBZZzTtMNnOMmbhWwHNneEtTsSQqcTtSKksVTtTdDwWRdDrlLzZtxzmUwWIiuKPNnpENzZHZzxnhkXxZGgUuuKVbBmMjJRtxXQSsqkJjkqQKeEsqQBbBhHHhHqNmMqoOpCpPcPAMmyYsZsKbBkSpmGgMpTifFvpPYyVpPIXPpxuljdDJTBMmeEdWtYyTLvTxXtVHTtvVOMmoEozIiOorRgnNKWgRrOorRZzkqQeIuUdbfFfJjFveEZvqQzZVdqQDqLnvRLloOrVKkvOzZpvVMYpPuUyseEPpvVSeJzgzmKnTyYtFfAabrRLpPrRalQqLVGnvVNgvMtTkKWRPpxXWwMORXdwWeWwJjlbBSsyoHkKgNnoOnNDdHhGFfWwKklLhOezZxXZgKkGEAawWezETLJnNPpjAalLuJjQqXxUMCBbtHhrRrRyQqQJjuUnNZQaAGCCcYsETkKtpPwWSbFfsEoOeZKkOozQYyJjVRryMPplLAaSspzNnZPqQWPpBbGgworROHzNiIxXSGCcgXIiCgIiGlLTgiccCkKlHJBulxUuLdDdDfFVvcCGgEkSsdszZSmMsSuURQqrDJjBKkVvFBpPjhHcSwWsMTWwtBAzAfFaHRrhtYyhOxXtQuUkgGYysSkGJjgqQMoKnIhHdRrpPDCLlMAaBaARrTtyYbmPsScyYTtyYWCkDdLlgGAhHanNFqOoTIitQKkmlFfzQZRrcCuUVFgaAGfveUueUTXGNSsngvvVdDiJRrlLenNMmEeERIiNWwUmMuEeSLdDfFlqQLFjJfBLfTYGmMgycCtpPUuMmsESlLsxXeSfUuFICgGEzYRrbiIBHCeDdsAaWwSxXlLlLsIGginNSUqQDWwYBblNHcjJcCoOsxXSuUTCctMmHURrIImMRCcrNnAaiJdDjAawWUuRrxgGXKmMkAaATtahHFfBbiIfXxXAaxQqQqcCoKeEHVvhexXzjhBbHCcAaTEeADddDadDuWwPpOIifUOxeEXDTZLAeGgVhHLavVJjtTlLADdlBdSsUeiIMmfJBbjQqNecCEVeEvBbcCCbBtTtTchXxTvVIOoEeiugGFfQqUYkKLebBDdEeesSvAazZFfzZTOotpEepPpPcVxLlQQpPZqQJnNRrjlLzDdhHVvFlQqQqQQgGafRrLuWyYwEejloOEelLeECDBbtTdvVqOohyYjWweEJsSBbTTtAYyViVvIyYYyRrOoLlvDnOoWpIizcCZPUaUQbiIByYquZzyHhYWXgaAZNVvnrRNPrRULltTQqpPxXUxXXxsnNbepPEsSKWtFffFVvTgqQFfGRYhHHwWhyYyrxXzCEecSlCcFJgGgGjKtEesWwubBDdUSNnKyYuFgDofFORrdGUuLYUDdHDdtTHhBaAhHcCaWwLlaAhEezLrRlOUOoHvVxXfKkLloCXxNnTtnNkKnYEsSeBbgOoLlHCGPpnwWzJvXFfxrEeJjxXcJIijCcQqMmGgCEhmgGKkSsMBbwWRjZztTrRJkKlLGgZGgzLbBlxXcCawcCxXKkJjtrRyYuUEtTeQqTzZzbcCBZAEWDLlEedwFFuUfQqrZlFfpoKkOFfqQqUBPpbTtIikiqQqQHGgHQtWwTrRVyYvyYqSsSsVFfvEhEeHkiIKeYyEVMGuUgmTtorRJcCEiIOaZzOoKkCfFbBGCjJcKkgSskKzZDdTtcHhnNuCqQcuUoOIipuUtTtTLlmkKMAPTBbNnZzTcECcjJtTexXEQqabBAXejtTJIiDhHwhHeEsBxLlVvXOHeEsQbBCcqiIOVvodxXaAuUDdAwLlWFfouZSsDBbdeElLvsbBVvKkrbBSEelkPpKLIcCeEKkZuUzLlEZzesPpSqDdIiaAQiFqHgGwPpCjJJOojcAaZsSmMzKeEkDFrRBbyGlpPLWiIwulLijVaACcQNnqNntUuqQRroOtbBMmwRTtKkrCFfclQqLJjWCcKcCNNnBbaAjaAJndDYyWnNwHhjJIiQKIBbihIspPvVpmMPfiInNmDpPpPdlLHjJyvUFaFfAsSfOoxXJjyYwWvhHUMmusPpSfFVQqtwRVwWvrLyYlSKkTtZzYrlRrvVeEKVvkAaDdRrkQqKqQaNnpWwyYPAaGgfkxXyYKFlLaUuKkucPAAQqaqfFGgeEQBLLliIvXafFAnNpPoOpzWwZVvHaAhEuUeqQPaFxXXxJuUKZOozzZNnkfFbBbnNBXxCmPuIhHiVszZSEfFCxTtXBbTtfXxuvnNVUxXuofFOyeEvVACceCcEvVUudMOoAamtuJjUqmMwWQTRrAaAaoOWwpPkKtcClLVrUuhHRxcCKOobBkToXqhMmHLgGvVGrCnzZNcvyYrRHhGCmMcgjJioOIlLrxXWTtXRrhHxhJjVvEoOiIKFfYdDNnQqywaAoZzOWjJiTkcCKvVIitdDXzbBZIiuftTFNnGgJsSmwAaurFfKktTvfjtTJoOFcaACVgsgeEGSLldRrBYJjylLQqbDoObBvRrtTwAaWwWRrDdaeyYpPmMlLSsriIGMmKktKkKkMyYWqQiIYykaAHMmsSGgLlCHKaAIkKcAaCikZzaAOoeNngTtSsUuBDDdpPWwaxXpPwEeWNTtneENnNPOooOpnVfmJNnyYjMFJjwWeGgGgELldiImMDMmeETtsSjlLkYybxyYJjXfFdzHTjQqaCmMIYytTibBpPAyYgGUNGgnBbjJurReJFfjETtvVzZVbSsBoMmJsSjOwWaAUogHhjJGpPhHnvQqntTDdEebBNHhaALgmMlLoOgVvGKfFkGOPjJKkpbZzmMnrRNyrtTyLlYEeEHhQqFZExXXMRrOoknNsSnAaIiZbJbjJBaKkuTtUNnRAaLExXwWirRQqIeEetlaAeiICxXnNvMAlLagZzEelJLlXzkKZtKkFfyYTfVvFeMtTBZQqzKkXxOoBGgbjJHhHOohKkVIivIyfFYjKvGgLlVRrvkQqKhXvVAacwWCSszhHZGgHnNhujJsSnNbBbBKkvVnNiIromOoMdDsFfSZHhJHRrGghaAxXWgGJmMmHhhHXKsSLlOZJLAalnNzZSsOojQqgVrRWNqQnmMwJjbBjSKRruUDdkksSKWSswYGgUUuuyxXCfFcmMZzOGgDQOowWZUuzjJJBbjyYqQTtjJhHSJjYysDxtTXdDFfItTiNbBAOofFaBbVVvAYMmNnyaaAlEeHhLUbBNnmMBYyRrRrKxBbdAaDRrXSsJjzZkAPpxHhXnoONaUXwWxYlLmMRYjJxXfrSpJSsjJjPphHPnNiIHhsqQFfiIvVBbRcCQldkKDLRrpPWfFwxXrRXxJrIiHPpSshRHKAasoVTtLlcCmGgmMMrDdRvdDOSgoORtTbBomMLMKoORbBnbUuUuBXYrRyrRZqEDdQqTOoRrtlnNGtTZzeEgeRcCJjkKanNGgAzZRrSuUtTrRzxXGaZzMmAgSlVvJjKvVklLbBThMmmMnxXdDlqQDdLBOogGgGKkpwVvxXRvVroWFSsEMDlXxLfezZmMxXEsSFKkfFdmeEeEvVesSrRCkKlLTtrRrRcfgGeLMmpWQqdCcDwRkKrpNHhmMnNnUujJbBNaACEebBIfFisSBYwLlaAUEezqcCgWwGQsvVcJfAYyaYyVbBdDJzZlLeFfmQqMUuIyJyYhszZSHaAjiIEenixRrXmMINxtTXzQqZDdWwsSfzZGJwLEeZzjSsKkJlWjPSVvspPlpPoOLRrXRrcCZzKLhHlhGgWwVvuUQDdTtTtqiIHJjcjJYQqOoyPpAJEenXERzZZzTtRRrmMoTtjJSNnsvViIZzHhyYxXeEKPoOhHpkuUueETtlLUAtrRrRBbTPuUphHIejkKmMJtTEPHyYVvGgWwbBkKnNlWwUuLYynKpTHhGgXxZzNntvVGdDgNkKnlLJjFZfFzHhfYyREerOoPVvoeEPpuUOPAnNMmLuUlwXxsSHnmMorRlLODNnGgQLFfFfliTEetVBrRbBVxXvKefnNiKvvVzTthHEeOoZYyiIZzVaATDkKdMmWzhHZwtaAOuUoWwLlklLVvbBHjJimnnNQqNMIiIqoOHhBRreEzFfUuZDxXdooOOrSsRbaYEeyBCFfcbfvVFwLDTtdNfKZJjhHYyBGgbCcTYhNhHPXxwWpncCyYoJnNIijOffsSFFOaAKXxLlkogGenBYyAffFFEeaguUzZGQqbrRPpSOsgiWwIhSrkKRSssfzZFIaRNnkxXEepPvBVvUzZunNxXwWzRriIIpPrRiWTtuiRrIqyYaAcRjJrCcCQlLXgiILlGIidqMNnFfuUmQgGKkZzKkDTtASUiIuxXeLlESeEsHhtTguUGIirRnNSsfFPpGghHioIDdiWCclfFLorRNSslLqQVFrbmMBVvaBvVHjJqQNwbBwDdWGBbgpCRrHhqQrXOfFoiIxgGGALlagSxXXxseepnNQygGRwWrYnNOoUuMmquUAabbBBCzjJZcNEaAoOenhOdDUGpPKnqQNVnAhHaNvCcBHhzZbkBbcCvERrZzyeyYIiEYNnewWnNSJEejjvXxVJjJVDdBbbBBbvbBFfiILlrXxoxleEwWRrLGgKkAaelLQqECcuUXgCMvTTviIVlQqWwtTGgSsCcKkqoiIOQTtAaPaAuUEepHfFhXxbBLKkJjSzZqQdDbtcEelZIimMzIiAaPpRrcTtFxpPXSsWwrgoSsOwWuAarkKRzZFfDdvyYpPYyVUGAaRQAagGkbOolLBKkWwnBbMmbBSssWEeOAaomXOoxCcyYtTyYRmRrMrMwWHhKtTkwvvdhHuUDmMxXOnNooGgOVVFfwWOoYyrmMdDRLltynNlLwWKkUuAaVCcvjJVWfeEAaSUusvVFYYoAwaqQARfIiooOxXKEekOFuUAdDRMmraKkYzZypPYyFuUFjJxwlJjLZzWnNXcCxcCnSsNawWAzYyZXSsIoOAaRruXxsfFwWDdSfoOFUjJLzZiEeTJjttTIvVBbqQcCRJjroxXHaOoALleUuEhOUNnMvVqFYyfQmEnNCcXxwAaWkKZKlIiIlLaAmMCcJGgFfFMmfGgyYWwuUIilLeHhMmPIisSiIhHzZqkAaKQaAvVjNnncCtTNJRrcCYsSWwyuWnaAoOTtNxXwPNnplLYHlLCJjjfHhFiIJYdxXPpibBxXIZMFnNfmzDrRoUuOyoOHhMfFFfIiMmmCzZameEZzMmjJMuUrRIiaAAoOFgnNBRrUYeEBbyepPcCEubBnwWuUEeuOoMFTtfmrGgsCcSRFfARranoONmhmrRMfFNnHvVxXEeVnfFLlHhNeERrvqgGMRrmQaAaGgAHUudYyAmMrRaZzvhHVYyWCcCcoOVbBvFQqfwcjJCDHhuUtTMRrdDmtrHhvVRBkKbyYbBqQqQDDdaATtIiAbvVIWaAfFFfwjJXxXxiuUNrEeRJZzzZjoOSsqQFlLfjJpPnNJNoAaOnpPtLlAtTvVKVvkazXxZMmwWlLhlLIiMmBbsDpPAafdDEAMOomZzaJjRryYehHvOgGzDdXxAaZgGpqEeQPoEhHefFLlSsjNCcfFJjoOuUnUnNajJAaAymMYIilsSyYTtLCkrRKcSHhShHvVDgYBoXAMdDDdmcCCnhPpGgHNcahTtAatuUuVToOtvHhUyNnmDdaAPpjLkKlRrEeJJjmxkcCpPKtTETthxXtTHhnNmMHGiICciNdkKdDDWwnTbyYNHhfFuMNnmpPzmTtMZpcCLOolEOuUCcouUeWwPUnkKdLlcCHiIhBbFfDWvVnNwOcoDdOCoLjJsSQUuCNrTyYiItzZwWQqPvVEeJLKklLlFRrfFfjJmMaAIizZsUQqRSsrpPSIisLCcuDdiIcEeJjCUuKzYXGgtQNnqPpXZzqMmLlgGUdpPOoSsOoDunNnNZPxwWXpzFfIKkrRaAxXgWvVwUudDaAfFbBZztTlXuUxFfLWRcCrEVveIiwyvVkKMmhHkKYbBNnnqQNqQeElLwQqhHOlLoWhHbIioOBRrwWewWtotxXtTTfzZFOsSTDzZNndDdDdkKXxjTtJJjAyYCcaXxtKkTQXIdvVdZuUzSsDAayIiYYysSnyYRTpPfFtrkKNrJOoyYRgGMmuUrnXxlLSsNPyYUqLxXlQaAPpMOomuILlipzXZzxlLVvFfzZMeEJjwlQRrqlUTtUufFuVkAaaAEHhenNWkeEKsSTtpPrRgGwATyNeEnRxXrAbKkBaCcqQYmMBbHCUuENnemLlTGfFgtOsmdDAadDVvqQvVVmMvGUuguUpHGghZiIzhHqQPSsTJjGzZgsSGLlPNnqQKKkkCcEeJjpuToOTttUOovVoUuAaTLlYyeEtjAaJfpPxXFAamMhFQqKmMkBbCFfcJsSTqaMmAQtjIioVvOMmfFfyhqQOoHeNnEYzZJjXTjhHJEetxPphHtTTtXJoOjGGggWwuNnUuQqnUuNYyLqdDqTAEeuUakKWMVvZzmwjRMiImrJjxXLllfBKkbeEflKkLVvmKkcCYyZzWwnNjJMFilLbhHlLDhHGWDjJjVvJdSLOolGgyYsGgUBbMIiKXxkYyYmMyBqQGgbcCmsDelDdLYrhHsSReEKkPppPFfMmzZdeEzVvqQcCOoSsUxZzeEEKkQqYyxXeXujJFLbBxXlzZqiCmMNncpFfPIRtTrjJWlLvetTEnCcNRrVeElZzLwqQYypPvVFfpPtThLlMmNaSsAnyYLlHXxjJXVCZzcgGvxPpLQOoqdDjEFirRIfHhBbuUeqQJvVSaAaAslFfjHhJrPpJwqQBbWjoORtIiTiITWIiwtLlcCEedDjUuWIiSRrGvVjJgsBwFfWaAbJsScCRrjfFrRGHhxPvUukIwWiKVpAavYysSPdDeEPpKkpreEGrlDdLRtzZqQnNTLyYYystdDTSOGgojJmMEwWelAACcaagRvVdwWeEqQDFfsSdDIirWUuKKkoOBbKkzZkKkmMxFyYfhHGXxgXGgRcDdRgHZzmMVvpKkPhxXHhGrCIifhHbQBbrRKkqrAaRbiIUTMmFftaAIiuBcCjaArRrsJgsSGPpjSkKdcCjJDfeEWwFmMdwWkWwkKRrKDaOkKojJASsqQXxdDqQEerRszZSRqQOoMKzZkzZpPBmMbmpPbfPgGpsSxXiIIiGWLlkokQqKOKoONeEnuBbjJUuUNnrlLBcCycClLmMYZzRrbsSYCcJLlrRjyBbTReEBbxXzaAZaAnNQqnNGgrgCcGtLeElrgGwJUujWRYlLtMmTyEhHefJjNnEeLlahcCHACKkcaAOorRFvVmMFrmIiLlMeEzZrRRgfFBbuUSsGRrnNfYykPpomMOIiKYbBhHzZyMmUUIiOPpHhorRupPdDuzZLlippPPxXTtLGghHlyYZziJjJjIlLqQZMJjaAmxVvrRtTGgUupPVHhAaUuiPoFNnxXfZzPcCpPPprRZzzZxXpMxXmmQEOoWwmhHEeUunUlLuRrNPHhpRrMBbiiIIeOKkoCbBcUEeugGQAaqwCcoMmzZwWGgPpjOEeoBbKkrRDdOoZzJxRnGgNqEegGtTXxjJstTOoSvVgvVAaGSsCcESslLOoEeNMIimNntTmMxXyYiInVveTkOoqHhQbBKEeCTgGtHyYJjNnzIiZhcVsSfFYykKOHhoRreEjsSXAasSYyJdDrIiRWwVCcvhVvizZrRkKAaeEhHWwBUTtuiaBnNbSsAMHlLmMrpPRhmGgwMmWIvVbIRkKDaAYydriSsaAsiIsSxXSHhYyrRuNnmMUdDrRzysSdDkBbKqSsQYXxOfFvVGgxOoqeEQXZVvOyYozjpeECczPpzZaAtTkKfFvVeEYyQqcCGgcCyMmYiIZPCcEZzyYoOoOotTYyQtTqOLllLstgrRPpaEwoOWbBeVvAGTMmJjYyIGgyfFYfeEFrrRfFRBbQqrRhmMxJjLCclxQqcCXXxrRZDdzheEHYyXTaXxAhrRHuUhEeFfHkKVveExXxXwWTthWwWwUuHtQqWdDwNnfMmFNnobBJjmIhHkKXxxXXxiBbpPIDMmcCpPvVsSgGdgGhSsIDdkYyKiEenNcCoOGIicCDdgHZCcDdKVvkzHhkDLldKWYywSJjsSxXYyyYUuVvJiIWwKSIiskkKHsSDdObBBbBboNDdnzZBbgGOKZzkHhVvodBZoFfOzbdYIiyBbmMmhHMGgCcdDbBetTpPEEeWlLJjyYwiIJjqQKzZxPpXkRDdBbrdDyYKTHYyhGgGgthHqQtTsSkqQqeEiIBbXxQKGgzZVvgGkKXxkeEoOAaNKfajJAsSFkZzIyYisArRUuaTtmMSUuenNRaOoAWwfFrTaAtMZxXzmEHhCiIpPWwLlrRcVvxXRSsdDraAvVGWwrRgTtnayYvWwVeEAEepPBbfeEFUujJwaAWOiIoMsSZzwWKVviIkIimauUNiYyInAvxXgGVvigqQGIHxXhVaAvwWFfVaAiIrRCzZcrUuOuUHwWhopPLPNnSspHhVvgGlcGgNnHhPpzZCiIhgYyyYyYSsGeEBfFiIbkKMmEevQqZzlLTnNttTVHRSsAaRrzZQqWcCwgkKTtaAltTLdDUuGbEeBbkKMjJjmRrMjJaAfFBbBbJxXFfAnNaIiIaAvViVkLlKiIvwWwWFfaeEGgxPpXiKkIAEeagGABbuUYyrRQQquUEpPeWwIigGqRrZzAOogSsGaPpnNFZzUumMfFrRIizZfXmMxXxeWwTuUhNmMnmHhMHSssoOStPoOpepPNnEZzEtTjgGzWfFwZvVfFxXJTtMmMHhtTeEmhtTBbHhcVvCmMmMUuKkxdzZDXPpePpYyEGfNnlIiLFfJjKkpoONfmMFLlnEexRruUPpXUuCcPFfJjzCciIZRravVApZzaRrRnNrAVvwWbBZzWwtTkKNnyYlrBbRqicCFfAaURruIHhTgFfGtKEeKkCDnNdcxXiIkQnNFfkKrRpPqQXxTtDVvVmMvKLlIgYyGikDtThHduPpUGgnNbBCEDkKSKnNksdZzeCcEaAyYubBQqSsHhXpPyYxFfUeIiEHdSlLswWDCMpFDdfPmchMNnpPuUmtTMmEgGgGeRRcmMCHhrWwYyrZzDdrRPeEiIlLnwWMmNEepTjJfFDlLdMmATgGJjJlLjlLthZsLlRSsrWwSCczHfFzZfFjJalLMBbsAaSxXBbowWdDczyYZzpPZyYtZzTCyYvbBVOmmEeSsMaAiIFoOpoOHhhHKHhHhXxXxkuEbUutTBejJzZLlvKkVxXNHhnsvVXxSrRqQFiIftVvTqRrQoaAOMmmMfFUvVNnuurRUEJrRjuUhHuUZzgGTGgtKkEedDrRLIilpPeMmMTKktnNHhKkPpmlLjJZGgzlLJUkKuwWUgGuvVcCUZzuLKklAaKkqpstTScCCcPoZzOJjvvVEqQepPxwWXdDVvXxQxXadDoOkKAqMmEqQwWeVQoOYeEsSyeEyYjuhHvVXxUzZEeDdCcoOOoAVbBvcCmMaLRrlyYlLRruUGwWtsSkUuKwWTFfgsSTZzFfVyxXYTtvcuUCtuUGgRzgGZcCZzvVEeUurVvZzHheEhHUUojJOuuMmUXxgAvVDdapgGPgGKkiIqQGrRufFfFWwFfSsDqXxbiIBQGgCcyYnNbBdu";

    static final String DAY2 = "wkzhyfdpluzeqvajtbbosngkxc\n" +
            "wrzhyfdplumeqvajtbioskfksc\n" +
            "wrzhyfdolumyqvajtbiosngkxs\n" +
            "urzhyfdplbmeqvrjtbiosngkxc\n" +
            "wrzhyfrulumeqvajtbiosngkxf\n" +
            "wrzhnfdprumvqvajtbiosngkxc\n" +
            "wrzhyfpplumeivajtbiosrgkxc\n" +
            "wrzhybdplumeqvaftbiosxgkxc\n" +
            "wrzgyfdplumeqvaltbiosngcxc\n" +
            "wrzhbfdzlumeqvajtbipsngkxc\n" +
            "wrzhyfdplumwqeajtbiosngxxc\n" +
            "wrzhyddplumeqvajtbimsngkxo\n" +
            "wrzhyfdplumsqdajtfiosngkxc\n" +
            "wrzvyfdplumeqjajtbkosngkxc\n" +
            "wrzhyfddlumeqvujtbiosngxxc\n" +
            "nrzhywdplumeqvajtbtosngkxc\n" +
            "wzzhyfdplomeqvdjtbiosngkxc\n" +
            "urzhyfdplumeqvaatbiosngkxb\n" +
            "wtzyyfdplumeqvajtbiosngkxl\n" +
            "wrzhyfdhlumnqvajebiosngkxc\n" +
            "wrzhyfdplumpqvajtbiosnyjxc\n" +
            "wrzdyfdplumeqvajtviospgkxc\n" +
            "wrzhyfdplzmeqvajtbiosegfxc\n" +
            "wrzhyfdglumkqvajtwiosngkxc\n" +
            "wrzhtfdplgmeqvaqtbiosngkxc\n" +
            "wrzhyfdplumeqvajxpiosngexc\n" +
            "frzhyfdplvmeqvajtbiosngkxp\n" +
            "wrzhyfkplumeqvajtbfosngkpc\n" +
            "irzhyfqplumeqvajtbiosngktc\n" +
            "wrzhyfdpluvoqvajtbioingkxc\n" +
            "wrzhyfdplumemvautbuosngkxc\n" +
            "wfzhyflplumeqvijtbiosngkxc\n" +
            "wrzhyfdglumeqvzjkbiosngkxc\n" +
            "wrzcyfdplaneqvajtbiosngkxc\n" +
            "wrzhbfdplumeqwajtbiosngpxc\n" +
            "szdhyfdplumeqvajtbiosngkxc\n" +
            "krzhyfdplumeqvajtxilsngkxc\n" +
            "brzhyfmplumexvajtbiosngkxc\n" +
            "wrzhyvdplumeqvajtbiosiglxc\n" +
            "orzhyfdplumeqvaotbcosngkxc\n" +
            "hrzhyfdplumeqvaptbiocngkxc\n" +
            "wwzhyfdklummqvajtbiosngkxc\n" +
            "wrzhyfdppymeqvvjtbiosngkxc\n" +
            "wsehyudplumeqvajtbiosngkxc\n" +
            "wrzhyzpplumeqvajtbioscgkxc\n" +
            "wrzhyfqpyumeqvajtbiusngkxc\n" +
            "urzhyfdpdumeqmajtbiosngkxc\n" +
            "wrzhyfdrlumxqvajtbiosnakxc\n" +
            "wrthyfdplumeskajtbiosngkxc\n" +
            "wrzhyfdplymeqvajtbiesbgkxc\n" +
            "wrzhyfdqlumeqyajtciosngkxc\n" +
            "trzhsfdplumeqyajtbiosngkxc\n" +
            "wpzhyfdplimeqvajtbiosngfxc\n" +
            "wrzhhfdplrmeqvajtbiosngkxx\n" +
            "wrzhycdpgumeqvajtbioslgkxc\n" +
            "wrzhyfdelumeqvajgtiosngkxc\n" +
            "wrzhyfdplutebvajtbiosygkxc\n" +
            "wrzhyfdplukeqvajtbiorngkec\n" +
            "erzhyfdilumeqvajwbiosngkxc\n" +
            "wrzhyfdplumnivxjtbiosngkxc\n" +
            "wrzhyfdplumebvajtoiovngkxc\n" +
            "wrzhyfdplumeqvajtbtosnwkxe\n" +
            "wrzhyfxplumevvajtciosngkxc\n" +
            "wbzhyfdxlumeqvajtriosngkxc\n" +
            "wrzyyfdplumeqvajmbiosngkxb\n" +
            "wfthyfdplumeqvajtbiosngkdc\n" +
            "wrzhuedplumeqvajtbgosngkxc\n" +
            "wrzhifdplumeqvajtsrosngkxc\n" +
            "wrzhyfdplumqqvajtaiosngkac\n" +
            "krzhyfvvlumeqvajtbiosngkxc\n" +
            "wrzhyfdplzmeqqajtbiosngkvc\n" +
            "wrzhyfqplumeqvajtbiosngdxy\n" +
            "wrzhyfdpluieqvajtbiosngjjc\n" +
            "wrzhyfdvlumeqrajdbiosngkxc\n" +
            "wrzhcfdpxumeqvajtbiotngkxc\n" +
            "wrzhyfdprumeqvaitbiosngexc\n" +
            "wrzhygdplumeqvpjtbiasngkxc\n" +
            "wrzhyndplumeqvajtpiosngkoc\n" +
            "wrzhyfdplumedvajtriowngkxc\n" +
            "wezjyndplumeqvajtbiosngkxc\n" +
            "wrmhyfdplumewiajtbiosngkxc\n" +
            "wrztyfdplumeqnajtbiobngkxc\n" +
            "wrzhyfdpyumeqvajjbijsngkxc\n" +
            "wrfhyfdplumeqkajtbiosnqkxc\n" +
            "wrzhyfdpllmezvartbiosngkxc\n" +
            "wszhyfdplumeqeajtbiqsngkxc\n" +
            "wrzhyfdplumeqwajtbnosnikxc\n" +
            "wrzvyfqulumeqvajtbiosngkxc\n" +
            "drzhyfdplureqvajtbiqsngkxc\n" +
            "wrzhyfdplumeqjamtbigsngkxc\n" +
            "wrzhyfdplumeqvajbbiosngzrc\n" +
            "grzhyfdpuumeqvajtbicsngkxc\n" +
            "wrrhyfdplumeqvajtgiosnggxc\n" +
            "wrzhyfkplumvqvajtbiosnhkxc\n" +
            "wrzhyfdplumeqvajtbicspgkxa\n" +
            "wrzhyfdplumeqvajtiiosnggoc\n" +
            "wfzhyfdplumyqvaytbiosngkxc\n" +
            "wrzhyfdpcumeqvajtbibsnfkxc\n" +
            "wrzhyfdplumeqvajtbigsnzkmc\n" +
            "wrzhyfdplcmeqvqjtriosngkxc\n" +
            "wrzhypdplwmeqvajtbiosnvkxc\n" +
            "wrziyfdmlumeqvaatbiosngkxc\n" +
            "wkzhyfdplsmeqvajobiosngkxc\n" +
            "wrzhyfdplumeqvkjvliosngkxc\n" +
            "wzzbyfdplumeqvajtbiolngkxc\n" +
            "wrzhyfdplvweqvajtbipsngkxc\n" +
            "wrzhyvdplumeqvujtbiosnfkxc\n" +
            "wrbhyfdplumedvajtbiosnhkxc\n" +
            "wrzhofdplumeqvajtbiosnskxy\n" +
            "wrzhyfdplumeqvaetbiohwgkxc\n" +
            "wezhyfoplumeqvajtbiosngmxc\n" +
            "wrzhykdblumeqvajtbiosngkjc\n" +
            "wrzhyddplwmeqvajtbioungkxc\n" +
            "wrzhyldplumqqvajpbiosngkxc\n" +
            "wrzhyfdtlumeqvajtbiusngkcc\n" +
            "wrzhyfdilumeqvajtbiosdgkxk\n" +
            "wrvhywdplumeqvajtbiosngoxc\n" +
            "wrzhyfdpyumeqvajtkiomngkxc\n" +
            "wrzhyfdpbummqvajtbiosngcxc\n" +
            "wrzhyfdpljmeqvajlbiosngjxc\n" +
            "wrzhyfdpmuieqdajtbiosngkxc\n" +
            "wrzgrfdplumeqvaktbiosngkxc\n" +
            "wrzhgrdpluueqvajtbiosngkxc\n" +
            "wazhyfdplhmeqvaqtbiosngkxc\n" +
            "wruhyfdplumeqvujtliosngkxc\n" +
            "wrzvyfdslumeqvajtbiwsngkxc\n" +
            "wrzhyfdplumeivactbiosqgkxc\n" +
            "wrzpyfdpbumeqvajtbioszgkxc\n" +
            "bgzhyfdplumeyvajtbiosngkxc\n" +
            "wrzhyfkplumeqvajtbiotngkxh\n" +
            "wrdhyfhplumexvajtbiosngkxc\n" +
            "brzhyfdpgumeqvgjtbiosngkxc\n" +
            "arzhqfdpeumeqvajtbiosngkxc\n" +
            "wrzhyftplumeqvajfbiosnykxc\n" +
            "wrzhyfdpcumeqvartbtosngkxc\n" +
            "wrzhvfzplumdqvajtbiosngkxc\n" +
            "wrzhyfdjlumeqvaetbiosjgkxc\n" +
            "wrbhyfdplumeqvajjjiosngkxc\n" +
            "wrxhyfdplumeqvajtbiyskgkxc\n" +
            "wpzhyydplumeqvajtbiosqgkxc\n" +
            "wrzhyfdplumzqvajtbzoongkxc\n" +
            "crzhyfdplnmeqvajtbjosngkxc\n" +
            "wrzhyfdpluveusajtbiosngkxc\n" +
            "wrzhypdpluyecvajtbiosngkxc\n" +
            "wrnhyfdplumeqvajtbioengoxc\n" +
            "wrzhypdplumefvajybiosngkxc\n" +
            "wrzhyfdplumeqvattviosngkdc\n" +
            "wrzhyfdplqmeqbajtbiostgkxc\n" +
            "wrghyfdpluveqvajtbiosngkxn\n" +
            "wryhyfdplumeqvajrbiopngkxc\n" +
            "wrzhyqdplumeqvajtbiwsngkxt\n" +
            "wrmoyfdpluheqvajtbiosngkxc\n" +
            "wrghyfdnlumeqvyjtbiosngkxc\n" +
            "wrzpyfdplumevvaatbiosngkxc\n" +
            "wrzhyfdplumhqvajtbiodngxxc\n" +
            "wrzhyfdplumeqcajtbioyjgkxc\n" +
            "wrzhyfdplumeqvajabirsngkgc\n" +
            "wrzhtfnplukeqvajtbiosngkxc\n" +
            "wqzhyfdplumeqvajtbiopegkxc\n" +
            "lrzhzfdplumeqdajtbiosngkxc\n" +
            "wrzzyfdplumeqvajnkiosngkxc\n" +
            "wrzhyfdflmmeqvajmbiosngkxc\n" +
            "wrzoyfdplumeqvjjtpiosngkxc\n" +
            "wrzhyfdpmpmeqvajhbiosngkxc\n" +
            "krvhyfdplumeqvajtbiossgkxc\n" +
            "wrzhyfdplumeqvaetviosnmkxc\n" +
            "wrzhyfzclumeqvajtbiosngwxc\n" +
            "wrzhyfdpvumeqvajtbiosngkcv\n" +
            "wrzhyfdpoumeqvajtbiozngoxc\n" +
            "wrzhyfwglumeqvajtbxosngkxc\n" +
            "wrshyfdplumeqiajtbiosngklc\n" +
            "wrzhyfdptdmeqvajtziosngkxc\n" +
            "wrwhyfdplumeqqajtbiosngkxj\n" +
            "wrzlyfypldmeqvajtbiosngkxc\n" +
            "wrzhyfdplumeqvajtbirknikxc\n" +
            "wrzhyhdplumeqvajtbmosnbkxc\n" +
            "wyzhyfiplumeqvwjtbiosngkxc\n" +
            "wrzgyfsplumeqvajtziosngkxc\n" +
            "wrzhrfdllumepvajtbiosngkxc\n" +
            "wrzayfdplumeqvajtbiosqgktc\n" +
            "whzhyfdplemnqvajtbiosngkxc\n" +
            "wazhyfdplumeqvaptbioongkxc\n" +
            "wrzhyfdpluueqvajtbiosnglvc\n" +
            "wrzhyfdplumjqvajmbionngkxc\n" +
            "wrzhofdplvmeqvajtbiosnqkxc\n" +
            "wrzhfidpluzeqvajtbiosngkxc\n" +
            "wrzhlfdpcumeqvaatbiosngkxc\n" +
            "wrzhyfdplumewvajtbiosigkoc\n" +
            "wrzjyfdplwmeqvajtbiodngkxc\n" +
            "wrzhyfdplumeqnsjtniosngkxc\n" +
            "wrzxyfdplxmeqvajtbiosngkdc\n" +
            "wrzhyfdplumpqvartbuosngkxc\n" +
            "orzhyfdplumeqzsjtbiosngkxc\n" +
            "wrzhyfdplumebvawtbiosngkxt\n" +
            "wrztyfdulumeqvajtbiosngkxy\n" +
            "wrzhytdplumeqvajtbznsngkxc\n" +
            "wrzhvfdplumeqvajtbinsngkxu\n" +
            "wezmyfdplumeqvajhbiosngkxc\n" +
            "wrzhhftplumeqvajtaiosngkxc\n" +
            "wrzhyfgplumeqvajtbioskgjxc\n" +
            "ujuhyfdplumeqvajtbiosngkxc\n" +
            "wryhymdplumeqvaftbiosngkxc\n" +
            "wrzhygdplumeqvajibiosfgkxc\n" +
            "frzhyfdppumeqvavtbiosngkxc\n" +
            "wruhyfdylumeqvajzbiosngkxc\n" +
            "wrzhzfdplumeqvajtbwosygkxc\n" +
            "wrzhyfdplumeqvaeteiojngkxc\n" +
            "wrhhyfdplumeqvajtiiopngkxc\n" +
            "irzhyfdplumeqvajtbiosngryc\n" +
            "wrvhycdpzumeqvajtbiosngkxc\n" +
            "wrzhyfdplumyqwajtboosngkxc\n" +
            "wrzthfdplumeqvajtbioengkxc\n" +
            "mrzhyfdalumeqvajtbiosngcxc\n" +
            "wrzhyfdflumpqvajtbiosngkmc\n" +
            "wrzhyfxplumeqvajtbiosnvkxo\n" +
            "wrzhyfdglumeqvajtbiosnfkxu\n" +
            "wrzhyfdnrumeqpajtbiosngkxc\n" +
            "wrzhlipplumeqvajtbiosngkxc\n" +
            "wrzhkfdplumemvajtbiohngkxc\n" +
            "wrziyfdmlumeqvaatbiosngkoc\n" +
            "wrzhyfdplwceqvajtbiosngzxc\n" +
            "wrzhyfdplumpqvactbiosngixc\n" +
            "wdzhyfdhdumeqvajtbiosngkxc\n" +
            "wnzhyfdplumeqvajtbbosngrxc\n" +
            "wrzhyfdblumeqvajtbiosngvxw\n" +
            "wyzhyfxpkumeqvajtbiosngkxc\n" +
            "wrzhywdplumjqvajgbiosngkxc\n" +
            "wrzhyfdpxumhqvajtbiokngkxc\n" +
            "wrzhxfpplumeqvajtbiosngkxk\n" +
            "mrzhyfdplumeqkajubiosngkxc\n" +
            "drzhyfdplumeqvajtbioingnxc\n" +
            "wrekyfdplumeqvajtbidsngkxc\n" +
            "wrzhyfdplumeanyjtbiosngkxc\n" +
            "wrzhyddpluzeqvajtbiosngtxc\n" +
            "wrzfyfdplumeqvaqtbiosngkxr\n" +
            "wrghywdpluneqvajtbiosngkxc\n" +
            "wrzhyfdplueeqvaptbioyngkxc\n" +
            "wrzhyqdpllmeqvajtbiosngdxc\n" +
            "whzhyfdxlumeqvajtbiosngksc\n" +
            "wrzjyfdplumeqvuitbiosngkxc\n" +
            "brzhyfdplumeqhajtbiolngkxc\n" +
            "wrzhyfqclureqvajtbiosngkxc\n" +
            "wwzhpfdplureqvajtbiosngkxc\n" +
            "wrzhyfdplumeqvavebijsngkxc\n" +
            "wrzhyfdpuumeqvajtsiosnkkxc\n" +
            "wrxhyfuplumeqvajtbiosngpxc\n" +
            "wrzhyfdplumeqvujlbiospgkxc\n" +
            "wrzvyfdplumeqvajtbiwsngpxc\n" +
            "wrzhyndplumeqvajtbiwsnekxc\n" +
            "wrzhkfdpoumeqvautbiosngkxc";

    static final String DAY3 = "#1 @ 306,433: 16x11\n" +
            "#2 @ 715,698: 18x29\n" +
            "#3 @ 955,34: 21x24\n" +
            "#4 @ 226,621: 16x23\n" +
            "#5 @ 528,35: 10x17\n" +
            "#6 @ 396,408: 13x24\n" +
            "#7 @ 144,524: 23x29\n" +
            "#8 @ 746,103: 24x15\n" +
            "#9 @ 818,839: 13x23\n" +
            "#10 @ 800,915: 20x14\n" +
            "#11 @ 801,660: 21x19\n" +
            "#12 @ 926,42: 20x10\n" +
            "#13 @ 124,229: 22x13\n" +
            "#14 @ 834,869: 15x26\n" +
            "#15 @ 152,128: 18x19\n" +
            "#16 @ 712,519: 14x26\n" +
            "#17 @ 588,602: 12x17\n" +
            "#18 @ 165,921: 22x27\n" +
            "#19 @ 648,842: 25x14\n" +
            "#20 @ 707,710: 15x20\n" +
            "#21 @ 641,116: 25x18\n" +
            "#22 @ 357,6: 21x12\n" +
            "#23 @ 670,927: 13x14\n" +
            "#24 @ 217,430: 6x6\n" +
            "#25 @ 573,283: 22x11\n" +
            "#26 @ 329,438: 13x16\n" +
            "#27 @ 562,894: 12x23\n" +
            "#28 @ 496,2: 26x29\n" +
            "#29 @ 406,346: 25x19\n" +
            "#30 @ 930,393: 16x16\n" +
            "#31 @ 530,830: 13x20\n" +
            "#32 @ 569,314: 11x18\n" +
            "#33 @ 225,621: 10x13\n" +
            "#34 @ 925,591: 29x15\n" +
            "#35 @ 543,331: 26x26\n" +
            "#36 @ 225,619: 12x22\n" +
            "#37 @ 168,437: 24x28\n" +
            "#38 @ 449,11: 15x15\n" +
            "#39 @ 665,949: 28x24\n" +
            "#40 @ 794,927: 5x4\n" +
            "#41 @ 70,333: 10x25\n" +
            "#42 @ 131,744: 18x16\n" +
            "#43 @ 668,276: 25x23\n" +
            "#44 @ 912,559: 25x14\n" +
            "#45 @ 72,416: 18x22\n" +
            "#46 @ 798,341: 13x13\n" +
            "#47 @ 231,104: 24x19\n" +
            "#48 @ 498,965: 24x13\n" +
            "#49 @ 684,424: 18x27\n" +
            "#50 @ 550,669: 11x13\n" +
            "#51 @ 169,964: 29x27\n" +
            "#52 @ 155,655: 22x12\n" +
            "#53 @ 256,981: 12x14\n" +
            "#54 @ 588,972: 17x27\n" +
            "#55 @ 57,851: 16x15\n" +
            "#56 @ 762,508: 26x28\n" +
            "#57 @ 407,887: 20x27\n" +
            "#58 @ 308,845: 19x23\n" +
            "#59 @ 93,594: 27x25\n" +
            "#60 @ 902,489: 19x16\n" +
            "#61 @ 151,454: 26x15\n" +
            "#62 @ 202,76: 20x14\n" +
            "#63 @ 402,981: 26x18\n" +
            "#64 @ 795,859: 13x21\n" +
            "#65 @ 725,643: 29x22\n" +
            "#66 @ 561,344: 29x20\n" +
            "#67 @ 938,178: 15x18\n" +
            "#68 @ 63,905: 27x13\n" +
            "#69 @ 920,703: 28x11\n" +
            "#70 @ 839,762: 24x22\n" +
            "#71 @ 277,626: 11x21\n" +
            "#72 @ 123,869: 25x25\n" +
            "#73 @ 343,66: 17x15\n" +
            "#74 @ 239,364: 25x10\n" +
            "#75 @ 785,758: 17x26\n" +
            "#76 @ 23,683: 26x28\n" +
            "#77 @ 32,855: 29x13\n" +
            "#78 @ 820,967: 11x27\n" +
            "#79 @ 353,9: 28x18\n" +
            "#80 @ 890,412: 24x25\n" +
            "#81 @ 395,894: 26x16\n" +
            "#82 @ 893,370: 11x26\n" +
            "#83 @ 96,30: 24x16\n" +
            "#84 @ 644,345: 17x29\n" +
            "#85 @ 884,19: 16x19\n" +
            "#86 @ 322,948: 14x12\n" +
            "#87 @ 602,415: 13x23\n" +
            "#88 @ 277,890: 28x16\n" +
            "#89 @ 173,916: 23x20\n" +
            "#90 @ 668,818: 21x15\n" +
            "#91 @ 569,66: 20x17\n" +
            "#92 @ 745,803: 12x14\n" +
            "#93 @ 114,610: 26x19\n" +
            "#94 @ 75,249: 15x11\n" +
            "#95 @ 746,481: 16x14\n" +
            "#96 @ 484,727: 29x24\n" +
            "#97 @ 759,476: 18x11\n" +
            "#98 @ 891,805: 25x19\n" +
            "#99 @ 80,8: 22x11\n" +
            "#100 @ 748,813: 11x29\n" +
            "#101 @ 508,370: 26x10\n" +
            "#102 @ 766,195: 11x22\n" +
            "#103 @ 331,83: 21x19\n" +
            "#104 @ 304,908: 10x21\n" +
            "#105 @ 950,925: 29x10\n" +
            "#106 @ 251,190: 18x23\n" +
            "#107 @ 773,884: 19x27\n" +
            "#108 @ 139,595: 12x29\n" +
            "#109 @ 683,721: 25x14\n" +
            "#110 @ 689,690: 28x12\n" +
            "#111 @ 555,890: 15x14\n" +
            "#112 @ 940,365: 25x20\n" +
            "#113 @ 220,299: 16x17\n" +
            "#114 @ 244,290: 27x17\n" +
            "#115 @ 510,653: 10x29\n" +
            "#116 @ 702,843: 20x22\n" +
            "#117 @ 467,603: 14x17\n" +
            "#118 @ 306,964: 28x23\n" +
            "#119 @ 265,848: 19x17\n" +
            "#120 @ 458,941: 11x19\n" +
            "#121 @ 914,875: 22x27\n" +
            "#122 @ 590,979: 10x3\n" +
            "#123 @ 940,243: 17x20\n" +
            "#124 @ 406,9: 14x27\n" +
            "#125 @ 929,88: 27x18\n" +
            "#126 @ 782,750: 28x24\n" +
            "#127 @ 510,804: 19x20\n" +
            "#128 @ 272,487: 22x11\n" +
            "#129 @ 215,116: 16x22\n" +
            "#130 @ 965,108: 29x20\n" +
            "#131 @ 894,931: 22x24\n" +
            "#132 @ 458,123: 20x18\n" +
            "#133 @ 230,544: 14x4\n" +
            "#134 @ 327,455: 21x13\n" +
            "#135 @ 423,950: 27x19\n" +
            "#136 @ 452,700: 26x24\n" +
            "#137 @ 931,597: 15x4\n" +
            "#138 @ 64,400: 27x17\n" +
            "#139 @ 144,59: 17x22\n" +
            "#140 @ 789,888: 29x22\n" +
            "#141 @ 628,288: 25x24\n" +
            "#142 @ 224,602: 10x24\n" +
            "#143 @ 684,966: 12x21\n" +
            "#144 @ 941,35: 28x19\n" +
            "#145 @ 73,605: 22x11\n" +
            "#146 @ 462,635: 10x22\n" +
            "#147 @ 894,50: 18x28\n" +
            "#148 @ 123,956: 17x16\n" +
            "#149 @ 677,510: 11x22\n" +
            "#150 @ 294,366: 28x21\n" +
            "#151 @ 829,626: 29x19\n" +
            "#152 @ 49,100: 17x20\n" +
            "#153 @ 368,380: 10x16\n" +
            "#154 @ 820,376: 19x18\n" +
            "#155 @ 829,0: 15x13\n" +
            "#156 @ 950,848: 29x13\n" +
            "#157 @ 64,510: 19x29\n" +
            "#158 @ 216,374: 14x18\n" +
            "#159 @ 409,26: 16x10\n" +
            "#160 @ 314,698: 18x18\n" +
            "#161 @ 163,928: 20x16\n" +
            "#162 @ 729,402: 22x17\n" +
            "#163 @ 424,725: 18x13\n" +
            "#164 @ 173,34: 14x17\n" +
            "#165 @ 458,898: 22x20\n" +
            "#166 @ 105,285: 29x15\n" +
            "#167 @ 74,822: 24x23\n" +
            "#168 @ 641,872: 5x3\n" +
            "#169 @ 202,907: 24x23\n" +
            "#170 @ 535,63: 21x13\n" +
            "#171 @ 267,384: 25x29\n" +
            "#172 @ 601,785: 19x27\n" +
            "#173 @ 960,848: 19x16\n" +
            "#174 @ 514,971: 24x29\n" +
            "#175 @ 606,9: 10x15\n" +
            "#176 @ 263,859: 28x17\n" +
            "#177 @ 120,623: 15x18\n" +
            "#178 @ 594,375: 13x26\n" +
            "#179 @ 406,310: 20x14\n" +
            "#180 @ 221,678: 24x21\n" +
            "#181 @ 926,211: 12x13\n" +
            "#182 @ 681,342: 12x27\n" +
            "#183 @ 335,231: 27x21\n" +
            "#184 @ 144,186: 11x10\n" +
            "#185 @ 550,716: 10x10\n" +
            "#186 @ 640,803: 29x20\n" +
            "#187 @ 859,856: 29x25\n" +
            "#188 @ 892,551: 10x25\n" +
            "#189 @ 852,768: 11x20\n" +
            "#190 @ 488,513: 18x26\n" +
            "#191 @ 766,142: 12x14\n" +
            "#192 @ 780,891: 24x24\n" +
            "#193 @ 321,800: 21x28\n" +
            "#194 @ 165,29: 11x16\n" +
            "#195 @ 156,489: 14x14\n" +
            "#196 @ 187,538: 23x29\n" +
            "#197 @ 206,556: 19x10\n" +
            "#198 @ 207,297: 29x16\n" +
            "#199 @ 769,103: 15x22\n" +
            "#200 @ 664,559: 19x10\n" +
            "#201 @ 425,650: 21x15\n" +
            "#202 @ 152,230: 12x26\n" +
            "#203 @ 185,219: 10x13\n" +
            "#204 @ 438,153: 11x20\n" +
            "#205 @ 385,532: 11x10\n" +
            "#206 @ 911,900: 15x24\n" +
            "#207 @ 469,19: 16x19\n" +
            "#208 @ 775,874: 28x16\n" +
            "#209 @ 756,432: 20x21\n" +
            "#210 @ 587,950: 26x21\n" +
            "#211 @ 931,541: 17x15\n" +
            "#212 @ 795,982: 26x13\n" +
            "#213 @ 582,774: 20x13\n" +
            "#214 @ 160,48: 14x24\n" +
            "#215 @ 332,253: 4x23\n" +
            "#216 @ 596,355: 13x19\n" +
            "#217 @ 713,472: 28x17\n" +
            "#218 @ 787,784: 23x13\n" +
            "#219 @ 427,448: 28x11\n" +
            "#220 @ 622,650: 12x19\n" +
            "#221 @ 243,238: 26x11\n" +
            "#222 @ 359,844: 13x13\n" +
            "#223 @ 831,883: 29x19\n" +
            "#224 @ 935,864: 21x25\n" +
            "#225 @ 39,580: 21x20\n" +
            "#226 @ 440,489: 28x21\n" +
            "#227 @ 28,963: 14x17\n" +
            "#228 @ 655,613: 11x29\n" +
            "#229 @ 537,940: 19x28\n" +
            "#230 @ 518,523: 16x29\n" +
            "#231 @ 942,81: 11x25\n" +
            "#232 @ 835,584: 19x11\n" +
            "#233 @ 398,271: 16x13\n" +
            "#234 @ 457,103: 24x24\n" +
            "#235 @ 293,482: 26x14\n" +
            "#236 @ 838,366: 11x16\n" +
            "#237 @ 770,521: 12x22\n" +
            "#238 @ 948,375: 25x28\n" +
            "#239 @ 611,479: 19x18\n" +
            "#240 @ 279,613: 23x21\n" +
            "#241 @ 407,715: 24x25\n" +
            "#242 @ 848,190: 15x18\n" +
            "#243 @ 260,111: 15x11\n" +
            "#244 @ 384,898: 11x27\n" +
            "#245 @ 593,960: 16x19\n" +
            "#246 @ 298,127: 11x29\n" +
            "#247 @ 436,903: 10x26\n" +
            "#248 @ 865,562: 27x29\n" +
            "#249 @ 594,723: 10x24\n" +
            "#250 @ 106,113: 29x12\n" +
            "#251 @ 981,752: 14x12\n" +
            "#252 @ 856,371: 18x15\n" +
            "#253 @ 568,163: 19x10\n" +
            "#254 @ 973,966: 22x22\n" +
            "#255 @ 270,541: 29x21\n" +
            "#256 @ 899,494: 24x28\n" +
            "#257 @ 620,623: 15x27\n" +
            "#258 @ 665,379: 28x10\n" +
            "#259 @ 35,241: 19x22\n" +
            "#260 @ 347,751: 11x25\n" +
            "#261 @ 96,554: 23x13\n" +
            "#262 @ 668,223: 21x19\n" +
            "#263 @ 15,976: 26x16\n" +
            "#264 @ 793,338: 24x29\n" +
            "#265 @ 159,651: 20x16\n" +
            "#266 @ 456,785: 13x26\n" +
            "#267 @ 838,394: 11x20\n" +
            "#268 @ 504,505: 11x17\n" +
            "#269 @ 655,29: 29x18\n" +
            "#270 @ 791,808: 18x17\n" +
            "#271 @ 72,435: 16x18\n" +
            "#272 @ 56,907: 17x23\n" +
            "#273 @ 908,558: 20x29\n" +
            "#274 @ 839,655: 25x20\n" +
            "#275 @ 658,316: 28x27\n" +
            "#276 @ 304,574: 10x12\n" +
            "#277 @ 699,205: 12x17\n" +
            "#278 @ 172,920: 18x24\n" +
            "#279 @ 240,590: 11x28\n" +
            "#280 @ 456,447: 16x18\n" +
            "#281 @ 6,272: 17x26\n" +
            "#282 @ 885,516: 25x28\n" +
            "#283 @ 904,39: 11x17\n" +
            "#284 @ 559,700: 23x29\n" +
            "#285 @ 124,215: 29x17\n" +
            "#286 @ 646,721: 25x17\n" +
            "#287 @ 69,801: 18x16\n" +
            "#288 @ 374,383: 17x15\n" +
            "#289 @ 883,794: 11x22\n" +
            "#290 @ 612,484: 12x12\n" +
            "#291 @ 445,912: 24x21\n" +
            "#292 @ 449,956: 12x20\n" +
            "#293 @ 164,268: 29x27\n" +
            "#294 @ 758,473: 12x16\n" +
            "#295 @ 326,64: 20x23\n" +
            "#296 @ 745,345: 12x13\n" +
            "#297 @ 941,487: 11x29\n" +
            "#298 @ 766,739: 22x25\n" +
            "#299 @ 142,639: 19x23\n" +
            "#300 @ 582,193: 17x19\n" +
            "#301 @ 633,870: 19x13\n" +
            "#302 @ 532,236: 22x29\n" +
            "#303 @ 502,331: 21x19\n" +
            "#304 @ 595,788: 25x28\n" +
            "#305 @ 340,547: 17x29\n" +
            "#306 @ 971,675: 24x21\n" +
            "#307 @ 841,220: 27x15\n" +
            "#308 @ 44,599: 12x16\n" +
            "#309 @ 950,202: 25x12\n" +
            "#310 @ 180,422: 17x27\n" +
            "#311 @ 159,748: 26x25\n" +
            "#312 @ 431,871: 29x13\n" +
            "#313 @ 400,827: 14x22\n" +
            "#314 @ 530,541: 24x11\n" +
            "#315 @ 516,818: 14x20\n" +
            "#316 @ 117,320: 20x11\n" +
            "#317 @ 350,407: 20x12\n" +
            "#318 @ 660,340: 18x11\n" +
            "#319 @ 583,596: 7x10\n" +
            "#320 @ 27,560: 28x24\n" +
            "#321 @ 885,935: 6x9\n" +
            "#322 @ 391,581: 29x24\n" +
            "#323 @ 791,578: 27x12\n" +
            "#324 @ 757,675: 16x25\n" +
            "#325 @ 895,805: 15x15\n" +
            "#326 @ 110,212: 20x27\n" +
            "#327 @ 883,930: 11x19\n" +
            "#328 @ 404,699: 17x11\n" +
            "#329 @ 616,370: 18x28\n" +
            "#330 @ 810,410: 29x23\n" +
            "#331 @ 343,336: 20x16\n" +
            "#332 @ 825,695: 26x22\n" +
            "#333 @ 393,230: 17x12\n" +
            "#334 @ 438,11: 12x14\n" +
            "#335 @ 940,272: 20x12\n" +
            "#336 @ 473,759: 20x21\n" +
            "#337 @ 223,101: 11x21\n" +
            "#338 @ 690,429: 11x11\n" +
            "#339 @ 496,177: 16x21\n" +
            "#340 @ 236,18: 15x17\n" +
            "#341 @ 105,710: 12x24\n" +
            "#342 @ 406,403: 20x22\n" +
            "#343 @ 390,151: 17x29\n" +
            "#344 @ 532,80: 26x16\n" +
            "#345 @ 164,534: 18x26\n" +
            "#346 @ 833,30: 17x22\n" +
            "#347 @ 23,234: 18x15\n" +
            "#348 @ 355,734: 22x12\n" +
            "#349 @ 456,486: 27x11\n" +
            "#350 @ 93,200: 26x28\n" +
            "#351 @ 29,695: 20x27\n" +
            "#352 @ 93,972: 25x26\n" +
            "#353 @ 422,905: 18x19\n" +
            "#354 @ 104,862: 12x17\n" +
            "#355 @ 419,320: 18x27\n" +
            "#356 @ 379,366: 28x19\n" +
            "#357 @ 399,275: 22x24\n" +
            "#358 @ 338,249: 18x26\n" +
            "#359 @ 550,181: 24x22\n" +
            "#360 @ 3,789: 19x29\n" +
            "#361 @ 467,889: 16x23\n" +
            "#362 @ 238,83: 10x10\n" +
            "#363 @ 258,371: 10x20\n" +
            "#364 @ 73,622: 24x17\n" +
            "#365 @ 343,0: 13x25\n" +
            "#366 @ 523,909: 14x27\n" +
            "#367 @ 500,938: 18x13\n" +
            "#368 @ 16,113: 12x15\n" +
            "#369 @ 208,22: 15x18\n" +
            "#370 @ 813,957: 25x19\n" +
            "#371 @ 641,657: 23x26\n" +
            "#372 @ 629,522: 22x19\n" +
            "#373 @ 645,349: 23x12\n" +
            "#374 @ 248,340: 12x24\n" +
            "#375 @ 455,920: 19x29\n" +
            "#376 @ 974,872: 12x21\n" +
            "#377 @ 816,527: 29x26\n" +
            "#378 @ 264,519: 22x22\n" +
            "#379 @ 514,924: 27x15\n" +
            "#380 @ 155,378: 27x21\n" +
            "#381 @ 768,447: 17x26\n" +
            "#382 @ 873,54: 27x24\n" +
            "#383 @ 299,297: 18x21\n" +
            "#384 @ 231,28: 18x15\n" +
            "#385 @ 721,416: 20x19\n" +
            "#386 @ 532,536: 29x13\n" +
            "#387 @ 357,579: 29x28\n" +
            "#388 @ 536,833: 19x14\n" +
            "#389 @ 658,21: 27x20\n" +
            "#390 @ 807,721: 23x20\n" +
            "#391 @ 234,669: 17x29\n" +
            "#392 @ 0,376: 25x19\n" +
            "#393 @ 221,695: 15x21\n" +
            "#394 @ 407,418: 28x16\n" +
            "#395 @ 928,791: 21x17\n" +
            "#396 @ 144,472: 26x26\n" +
            "#397 @ 614,538: 27x19\n" +
            "#398 @ 506,320: 19x19\n" +
            "#399 @ 699,674: 14x14\n" +
            "#400 @ 741,832: 29x26\n" +
            "#401 @ 671,952: 27x10\n" +
            "#402 @ 368,426: 26x17\n" +
            "#403 @ 620,361: 21x18\n" +
            "#404 @ 945,76: 24x14\n" +
            "#405 @ 147,878: 10x11\n" +
            "#406 @ 805,658: 17x11\n" +
            "#407 @ 504,755: 28x24\n" +
            "#408 @ 231,85: 15x21\n" +
            "#409 @ 882,960: 22x10\n" +
            "#410 @ 118,222: 15x12\n" +
            "#411 @ 261,400: 13x25\n" +
            "#412 @ 213,602: 18x26\n" +
            "#413 @ 309,823: 27x25\n" +
            "#414 @ 967,897: 15x17\n" +
            "#415 @ 810,580: 25x26\n" +
            "#416 @ 477,728: 16x28\n" +
            "#417 @ 899,591: 13x14\n" +
            "#418 @ 764,81: 13x20\n" +
            "#419 @ 482,484: 27x10\n" +
            "#420 @ 651,853: 22x11\n" +
            "#421 @ 569,665: 11x22\n" +
            "#422 @ 715,324: 14x18\n" +
            "#423 @ 735,410: 17x11\n" +
            "#424 @ 90,67: 21x13\n" +
            "#425 @ 26,521: 19x16\n" +
            "#426 @ 600,434: 23x13\n" +
            "#427 @ 496,328: 29x21\n" +
            "#428 @ 880,554: 26x29\n" +
            "#429 @ 754,603: 21x18\n" +
            "#430 @ 299,695: 12x23\n" +
            "#431 @ 507,429: 10x14\n" +
            "#432 @ 254,40: 22x12\n" +
            "#433 @ 647,613: 14x11\n" +
            "#434 @ 134,924: 22x22\n" +
            "#435 @ 983,700: 12x24\n" +
            "#436 @ 516,923: 23x19\n" +
            "#437 @ 161,953: 19x19\n" +
            "#438 @ 966,733: 12x18\n" +
            "#439 @ 461,714: 29x21\n" +
            "#440 @ 141,639: 28x12\n" +
            "#441 @ 854,37: 19x24\n" +
            "#442 @ 66,444: 18x16\n" +
            "#443 @ 11,261: 10x20\n" +
            "#444 @ 154,475: 8x16\n" +
            "#445 @ 631,291: 15x6\n" +
            "#446 @ 616,2: 29x26\n" +
            "#447 @ 888,192: 19x28\n" +
            "#448 @ 426,969: 15x19\n" +
            "#449 @ 729,80: 22x12\n" +
            "#450 @ 732,433: 12x19\n" +
            "#451 @ 21,371: 18x12\n" +
            "#452 @ 542,46: 3x10\n" +
            "#453 @ 522,251: 11x26\n" +
            "#454 @ 522,119: 14x10\n" +
            "#455 @ 922,152: 28x16\n" +
            "#456 @ 970,711: 18x29\n" +
            "#457 @ 331,768: 20x16\n" +
            "#458 @ 731,591: 16x26\n" +
            "#459 @ 222,122: 10x20\n" +
            "#460 @ 889,360: 13x14\n" +
            "#461 @ 609,78: 13x27\n" +
            "#462 @ 75,527: 28x13\n" +
            "#463 @ 240,740: 29x29\n" +
            "#464 @ 6,402: 14x15\n" +
            "#465 @ 92,546: 17x15\n" +
            "#466 @ 945,264: 17x12\n" +
            "#467 @ 663,269: 13x11\n" +
            "#468 @ 470,445: 28x11\n" +
            "#469 @ 242,407: 14x17\n" +
            "#470 @ 319,432: 10x14\n" +
            "#471 @ 688,363: 14x19\n" +
            "#472 @ 754,60: 22x10\n" +
            "#473 @ 355,576: 21x15\n" +
            "#474 @ 936,512: 22x28\n" +
            "#475 @ 14,961: 16x21\n" +
            "#476 @ 792,698: 28x27\n" +
            "#477 @ 975,779: 12x15\n" +
            "#478 @ 16,99: 22x16\n" +
            "#479 @ 668,345: 12x21\n" +
            "#480 @ 803,285: 22x27\n" +
            "#481 @ 64,198: 25x15\n" +
            "#482 @ 677,394: 23x10\n" +
            "#483 @ 928,947: 23x26\n" +
            "#484 @ 728,947: 15x13\n" +
            "#485 @ 825,232: 15x23\n" +
            "#486 @ 160,418: 15x26\n" +
            "#487 @ 317,772: 21x29\n" +
            "#488 @ 944,838: 25x25\n" +
            "#489 @ 595,13: 15x22\n" +
            "#490 @ 957,64: 25x20\n" +
            "#491 @ 512,517: 21x27\n" +
            "#492 @ 425,883: 19x15\n" +
            "#493 @ 664,342: 20x14\n" +
            "#494 @ 446,558: 29x15\n" +
            "#495 @ 56,373: 18x12\n" +
            "#496 @ 22,950: 25x23\n" +
            "#497 @ 517,950: 12x18\n" +
            "#498 @ 554,920: 23x27\n" +
            "#499 @ 249,530: 22x17\n" +
            "#500 @ 940,167: 22x13\n" +
            "#501 @ 278,520: 26x15\n" +
            "#502 @ 419,965: 11x23\n" +
            "#503 @ 786,766: 14x25\n" +
            "#504 @ 214,681: 26x22\n" +
            "#505 @ 588,904: 20x13\n" +
            "#506 @ 937,970: 28x25\n" +
            "#507 @ 890,739: 20x11\n" +
            "#508 @ 656,379: 20x20\n" +
            "#509 @ 821,47: 22x22\n" +
            "#510 @ 927,666: 20x27\n" +
            "#511 @ 131,79: 20x28\n" +
            "#512 @ 810,55: 14x18\n" +
            "#513 @ 416,317: 10x22\n" +
            "#514 @ 2,959: 16x25\n" +
            "#515 @ 674,561: 4x5\n" +
            "#516 @ 199,107: 13x16\n" +
            "#517 @ 834,403: 27x25\n" +
            "#518 @ 206,337: 11x24\n" +
            "#519 @ 422,974: 22x11\n" +
            "#520 @ 120,930: 22x10\n" +
            "#521 @ 97,488: 3x11\n" +
            "#522 @ 276,222: 15x20\n" +
            "#523 @ 181,405: 21x22\n" +
            "#524 @ 622,690: 29x29\n" +
            "#525 @ 584,148: 20x12\n" +
            "#526 @ 428,971: 13x22\n" +
            "#527 @ 694,836: 23x26\n" +
            "#528 @ 611,90: 25x16\n" +
            "#529 @ 539,44: 11x20\n" +
            "#530 @ 145,374: 16x28\n" +
            "#531 @ 907,429: 14x21\n" +
            "#532 @ 214,368: 11x24\n" +
            "#533 @ 756,801: 10x26\n" +
            "#534 @ 175,731: 10x29\n" +
            "#535 @ 404,8: 15x20\n" +
            "#536 @ 808,645: 21x14\n" +
            "#537 @ 816,227: 27x29\n" +
            "#538 @ 665,816: 15x26\n" +
            "#539 @ 950,498: 16x27\n" +
            "#540 @ 596,212: 25x22\n" +
            "#541 @ 161,869: 24x23\n" +
            "#542 @ 946,18: 16x19\n" +
            "#543 @ 765,881: 17x14\n" +
            "#544 @ 354,909: 28x15\n" +
            "#545 @ 490,892: 12x10\n" +
            "#546 @ 322,251: 19x29\n" +
            "#547 @ 649,940: 7x6\n" +
            "#548 @ 65,646: 12x18\n" +
            "#549 @ 660,779: 10x11\n" +
            "#550 @ 693,377: 28x19\n" +
            "#551 @ 341,288: 29x17\n" +
            "#552 @ 28,317: 10x24\n" +
            "#553 @ 181,108: 29x17\n" +
            "#554 @ 445,602: 28x12\n" +
            "#555 @ 328,546: 21x21\n" +
            "#556 @ 973,193: 21x25\n" +
            "#557 @ 464,107: 12x24\n" +
            "#558 @ 196,586: 22x23\n" +
            "#559 @ 647,935: 12x17\n" +
            "#560 @ 973,250: 14x29\n" +
            "#561 @ 936,105: 14x12\n" +
            "#562 @ 550,726: 13x13\n" +
            "#563 @ 628,606: 18x23\n" +
            "#564 @ 926,696: 15x29\n" +
            "#565 @ 538,901: 18x28\n" +
            "#566 @ 869,126: 20x25\n" +
            "#567 @ 945,696: 22x10\n" +
            "#568 @ 340,816: 10x21\n" +
            "#569 @ 903,793: 27x28\n" +
            "#570 @ 139,307: 12x13\n" +
            "#571 @ 722,910: 14x23\n" +
            "#572 @ 90,952: 22x26\n" +
            "#573 @ 287,342: 10x28\n" +
            "#574 @ 539,59: 14x21\n" +
            "#575 @ 761,537: 27x23\n" +
            "#576 @ 670,683: 17x17\n" +
            "#577 @ 554,903: 28x28\n" +
            "#578 @ 571,799: 23x12\n" +
            "#579 @ 185,413: 16x12\n" +
            "#580 @ 277,828: 18x29\n" +
            "#581 @ 367,384: 19x22\n" +
            "#582 @ 63,264: 15x12\n" +
            "#583 @ 703,321: 21x29\n" +
            "#584 @ 725,331: 21x27\n" +
            "#585 @ 215,428: 11x14\n" +
            "#586 @ 560,678: 25x22\n" +
            "#587 @ 370,923: 27x21\n" +
            "#588 @ 304,943: 23x15\n" +
            "#589 @ 906,29: 25x18\n" +
            "#590 @ 325,547: 26x12\n" +
            "#591 @ 917,683: 14x25\n" +
            "#592 @ 650,75: 14x15\n" +
            "#593 @ 765,581: 27x19\n" +
            "#594 @ 332,345: 25x26\n" +
            "#595 @ 900,747: 20x27\n" +
            "#596 @ 393,888: 14x29\n" +
            "#597 @ 0,376: 14x20\n" +
            "#598 @ 782,607: 28x26\n" +
            "#599 @ 248,868: 13x13\n" +
            "#600 @ 841,842: 22x21\n" +
            "#601 @ 544,141: 13x17\n" +
            "#602 @ 418,413: 19x20\n" +
            "#603 @ 899,215: 28x21\n" +
            "#604 @ 100,136: 27x20\n" +
            "#605 @ 971,346: 11x26\n" +
            "#606 @ 147,466: 19x26\n" +
            "#607 @ 935,817: 12x15\n" +
            "#608 @ 341,593: 25x17\n" +
            "#609 @ 701,454: 24x22\n" +
            "#610 @ 546,903: 29x26\n" +
            "#611 @ 230,85: 15x22\n" +
            "#612 @ 442,155: 20x11\n" +
            "#613 @ 417,331: 13x11\n" +
            "#614 @ 150,495: 12x23\n" +
            "#615 @ 537,662: 17x25\n" +
            "#616 @ 57,249: 28x17\n" +
            "#617 @ 811,631: 17x11\n" +
            "#618 @ 176,933: 21x10\n" +
            "#619 @ 968,595: 29x28\n" +
            "#620 @ 206,563: 18x13\n" +
            "#621 @ 140,236: 21x10\n" +
            "#622 @ 393,352: 12x25\n" +
            "#623 @ 494,312: 22x25\n" +
            "#624 @ 607,576: 20x5\n" +
            "#625 @ 389,912: 27x19\n" +
            "#626 @ 389,29: 28x23\n" +
            "#627 @ 863,48: 23x16\n" +
            "#628 @ 972,198: 13x25\n" +
            "#629 @ 276,869: 15x26\n" +
            "#630 @ 553,930: 16x20\n" +
            "#631 @ 260,618: 12x19\n" +
            "#632 @ 465,805: 17x24\n" +
            "#633 @ 767,841: 12x12\n" +
            "#634 @ 791,925: 23x18\n" +
            "#635 @ 840,891: 16x6\n" +
            "#636 @ 856,399: 20x23\n" +
            "#637 @ 316,704: 14x19\n" +
            "#638 @ 50,429: 16x29\n" +
            "#639 @ 763,684: 15x28\n" +
            "#640 @ 243,397: 11x22\n" +
            "#641 @ 14,388: 20x26\n" +
            "#642 @ 245,778: 12x23\n" +
            "#643 @ 242,162: 17x29\n" +
            "#644 @ 56,573: 10x27\n" +
            "#645 @ 621,214: 14x23\n" +
            "#646 @ 109,328: 20x12\n" +
            "#647 @ 357,787: 22x11\n" +
            "#648 @ 285,542: 24x18\n" +
            "#649 @ 821,701: 29x14\n" +
            "#650 @ 938,535: 11x13\n" +
            "#651 @ 545,529: 11x25\n" +
            "#652 @ 487,479: 25x28\n" +
            "#653 @ 540,149: 14x19\n" +
            "#654 @ 683,305: 14x21\n" +
            "#655 @ 714,899: 17x14\n" +
            "#656 @ 514,131: 18x21\n" +
            "#657 @ 208,63: 13x28\n" +
            "#658 @ 695,948: 9x4\n" +
            "#659 @ 497,883: 13x13\n" +
            "#660 @ 259,647: 10x24\n" +
            "#661 @ 762,618: 16x18\n" +
            "#662 @ 890,590: 10x18\n" +
            "#663 @ 78,831: 10x26\n" +
            "#664 @ 963,246: 18x14\n" +
            "#665 @ 934,804: 13x20\n" +
            "#666 @ 193,787: 13x12\n" +
            "#667 @ 432,580: 6x15\n" +
            "#668 @ 352,412: 11x3\n" +
            "#669 @ 205,10: 21x29\n" +
            "#670 @ 574,331: 12x29\n" +
            "#671 @ 248,787: 3x5\n" +
            "#672 @ 395,383: 12x17\n" +
            "#673 @ 798,218: 19x22\n" +
            "#674 @ 500,665: 16x26\n" +
            "#675 @ 26,812: 10x14\n" +
            "#676 @ 456,794: 25x16\n" +
            "#677 @ 840,9: 20x12\n" +
            "#678 @ 657,859: 20x18\n" +
            "#679 @ 546,349: 24x10\n" +
            "#680 @ 682,764: 23x14\n" +
            "#681 @ 291,758: 14x26\n" +
            "#682 @ 734,98: 23x25\n" +
            "#683 @ 109,393: 19x28\n" +
            "#684 @ 398,336: 26x22\n" +
            "#685 @ 122,298: 18x26\n" +
            "#686 @ 54,539: 25x13\n" +
            "#687 @ 1,378: 24x23\n" +
            "#688 @ 512,587: 28x16\n" +
            "#689 @ 371,638: 20x14\n" +
            "#690 @ 745,526: 25x23\n" +
            "#691 @ 833,817: 11x9\n" +
            "#692 @ 318,692: 20x14\n" +
            "#693 @ 801,207: 22x24\n" +
            "#694 @ 134,117: 26x11\n" +
            "#695 @ 712,298: 23x29\n" +
            "#696 @ 554,141: 28x28\n" +
            "#697 @ 776,835: 24x24\n" +
            "#698 @ 76,388: 17x21\n" +
            "#699 @ 490,544: 27x13\n" +
            "#700 @ 91,493: 22x25\n" +
            "#701 @ 78,984: 22x12\n" +
            "#702 @ 328,602: 17x27\n" +
            "#703 @ 240,781: 29x29\n" +
            "#704 @ 818,642: 17x20\n" +
            "#705 @ 376,813: 21x28\n" +
            "#706 @ 86,598: 24x21\n" +
            "#707 @ 507,105: 23x23\n" +
            "#708 @ 828,736: 16x23\n" +
            "#709 @ 621,665: 27x17\n" +
            "#710 @ 99,250: 26x14\n" +
            "#711 @ 520,192: 25x26\n" +
            "#712 @ 94,479: 10x28\n" +
            "#713 @ 257,121: 10x22\n" +
            "#714 @ 72,143: 21x22\n" +
            "#715 @ 107,953: 28x29\n" +
            "#716 @ 635,808: 12x22\n" +
            "#717 @ 605,574: 29x11\n" +
            "#718 @ 676,217: 10x12\n" +
            "#719 @ 458,345: 25x25\n" +
            "#720 @ 462,552: 28x23\n" +
            "#721 @ 255,381: 28x13\n" +
            "#722 @ 609,975: 10x12\n" +
            "#723 @ 816,522: 20x12\n" +
            "#724 @ 742,644: 16x15\n" +
            "#725 @ 628,708: 11x16\n" +
            "#726 @ 123,759: 25x23\n" +
            "#727 @ 551,915: 16x14\n" +
            "#728 @ 851,119: 28x10\n" +
            "#729 @ 294,698: 19x19\n" +
            "#730 @ 679,511: 23x22\n" +
            "#731 @ 526,49: 29x15\n" +
            "#732 @ 802,716: 13x24\n" +
            "#733 @ 342,777: 20x11\n" +
            "#734 @ 470,97: 20x15\n" +
            "#735 @ 377,347: 21x23\n" +
            "#736 @ 408,291: 25x10\n" +
            "#737 @ 819,109: 21x14\n" +
            "#738 @ 247,657: 16x19\n" +
            "#739 @ 58,94: 18x11\n" +
            "#740 @ 636,701: 21x29\n" +
            "#741 @ 712,73: 28x18\n" +
            "#742 @ 924,532: 22x16\n" +
            "#743 @ 17,804: 24x10\n" +
            "#744 @ 177,244: 18x15\n" +
            "#745 @ 503,5: 27x26\n" +
            "#746 @ 393,783: 22x27\n" +
            "#747 @ 585,381: 23x10\n" +
            "#748 @ 807,634: 27x21\n" +
            "#749 @ 196,438: 15x20\n" +
            "#750 @ 52,203: 16x18\n" +
            "#751 @ 975,885: 21x15\n" +
            "#752 @ 914,232: 28x21\n" +
            "#753 @ 818,919: 10x10\n" +
            "#754 @ 867,141: 24x24\n" +
            "#755 @ 49,536: 28x23\n" +
            "#756 @ 937,817: 15x11\n" +
            "#757 @ 384,423: 20x15\n" +
            "#758 @ 816,498: 20x14\n" +
            "#759 @ 671,583: 23x13\n" +
            "#760 @ 511,582: 18x15\n" +
            "#761 @ 426,694: 26x23\n" +
            "#762 @ 70,610: 12x26\n" +
            "#763 @ 798,115: 26x22\n" +
            "#764 @ 693,384: 13x19\n" +
            "#765 @ 754,523: 15x13\n" +
            "#766 @ 12,110: 24x17\n" +
            "#767 @ 498,144: 24x27\n" +
            "#768 @ 106,222: 22x16\n" +
            "#769 @ 98,730: 29x20\n" +
            "#770 @ 849,846: 5x11\n" +
            "#771 @ 64,591: 21x10\n" +
            "#772 @ 218,476: 25x27\n" +
            "#773 @ 63,659: 22x24\n" +
            "#774 @ 177,798: 21x29\n" +
            "#775 @ 2,574: 10x18\n" +
            "#776 @ 340,568: 21x17\n" +
            "#777 @ 813,848: 11x22\n" +
            "#778 @ 796,625: 24x16\n" +
            "#779 @ 569,343: 14x22\n" +
            "#780 @ 289,925: 21x20\n" +
            "#781 @ 64,123: 26x29\n" +
            "#782 @ 201,554: 16x11\n" +
            "#783 @ 629,723: 28x20\n" +
            "#784 @ 972,748: 11x20\n" +
            "#785 @ 712,677: 27x16\n" +
            "#786 @ 718,73: 17x19\n" +
            "#787 @ 186,79: 17x23\n" +
            "#788 @ 844,627: 13x20\n" +
            "#789 @ 425,259: 12x23\n" +
            "#790 @ 399,805: 27x20\n" +
            "#791 @ 208,889: 14x29\n" +
            "#792 @ 274,24: 25x27\n" +
            "#793 @ 493,962: 21x20\n" +
            "#794 @ 337,547: 24x12\n" +
            "#795 @ 694,768: 7x6\n" +
            "#796 @ 766,927: 14x24\n" +
            "#797 @ 724,197: 23x10\n" +
            "#798 @ 525,163: 29x21\n" +
            "#799 @ 507,99: 25x18\n" +
            "#800 @ 565,726: 23x27\n" +
            "#801 @ 950,510: 28x21\n" +
            "#802 @ 86,593: 20x29\n" +
            "#803 @ 900,401: 25x26\n" +
            "#804 @ 478,528: 26x21\n" +
            "#805 @ 131,873: 27x21\n" +
            "#806 @ 636,685: 12x28\n" +
            "#807 @ 481,607: 18x27\n" +
            "#808 @ 817,899: 11x25\n" +
            "#809 @ 630,508: 20x21\n" +
            "#810 @ 540,145: 29x18\n" +
            "#811 @ 857,155: 18x17\n" +
            "#812 @ 448,213: 16x14\n" +
            "#813 @ 560,275: 23x24\n" +
            "#814 @ 580,591: 14x19\n" +
            "#815 @ 145,290: 20x25\n" +
            "#816 @ 894,544: 22x23\n" +
            "#817 @ 317,476: 18x14\n" +
            "#818 @ 247,863: 14x25\n" +
            "#819 @ 596,378: 26x25\n" +
            "#820 @ 633,824: 17x10\n" +
            "#821 @ 602,537: 17x21\n" +
            "#822 @ 8,134: 28x25\n" +
            "#823 @ 580,44: 20x27\n" +
            "#824 @ 394,541: 15x15\n" +
            "#825 @ 985,116: 13x24\n" +
            "#826 @ 423,416: 10x19\n" +
            "#827 @ 631,776: 27x15\n" +
            "#828 @ 866,358: 20x18\n" +
            "#829 @ 658,939: 19x12\n" +
            "#830 @ 522,799: 25x21\n" +
            "#831 @ 11,655: 12x19\n" +
            "#832 @ 266,624: 29x23\n" +
            "#833 @ 389,26: 21x12\n" +
            "#834 @ 480,174: 25x25\n" +
            "#835 @ 268,14: 16x15\n" +
            "#836 @ 906,838: 10x10\n" +
            "#837 @ 51,433: 28x21\n" +
            "#838 @ 556,351: 14x21\n" +
            "#839 @ 636,355: 22x20\n" +
            "#840 @ 593,596: 28x26\n" +
            "#841 @ 164,696: 24x13\n" +
            "#842 @ 723,685: 25x17\n" +
            "#843 @ 309,480: 11x14\n" +
            "#844 @ 566,911: 10x18\n" +
            "#845 @ 869,153: 23x12\n" +
            "#846 @ 391,46: 20x25\n" +
            "#847 @ 932,36: 25x15\n" +
            "#848 @ 426,915: 18x23\n" +
            "#849 @ 176,914: 10x27\n" +
            "#850 @ 203,331: 22x16\n" +
            "#851 @ 618,364: 20x12\n" +
            "#852 @ 918,550: 29x10\n" +
            "#853 @ 155,284: 14x12\n" +
            "#854 @ 616,601: 24x28\n" +
            "#855 @ 500,541: 14x14\n" +
            "#856 @ 838,205: 12x23\n" +
            "#857 @ 369,704: 26x28\n" +
            "#858 @ 199,706: 17x19\n" +
            "#859 @ 775,818: 26x11\n" +
            "#860 @ 197,702: 29x22\n" +
            "#861 @ 980,360: 14x16\n" +
            "#862 @ 180,877: 28x12\n" +
            "#863 @ 70,7: 28x13\n" +
            "#864 @ 379,372: 18x23\n" +
            "#865 @ 791,905: 20x20\n" +
            "#866 @ 356,618: 13x22\n" +
            "#867 @ 579,195: 14x18\n" +
            "#868 @ 221,610: 26x19\n" +
            "#869 @ 157,133: 28x21\n" +
            "#870 @ 929,292: 29x18\n" +
            "#871 @ 746,517: 16x12\n" +
            "#872 @ 606,351: 13x13\n" +
            "#873 @ 592,169: 28x29\n" +
            "#874 @ 456,511: 26x10\n" +
            "#875 @ 304,568: 16x22\n" +
            "#876 @ 802,596: 26x23\n" +
            "#877 @ 602,100: 16x12\n" +
            "#878 @ 434,891: 21x16\n" +
            "#879 @ 146,738: 25x25\n" +
            "#880 @ 876,603: 15x24\n" +
            "#881 @ 920,745: 13x23\n" +
            "#882 @ 521,3: 10x10\n" +
            "#883 @ 724,707: 18x22\n" +
            "#884 @ 90,28: 15x23\n" +
            "#885 @ 258,302: 10x12\n" +
            "#886 @ 38,410: 23x27\n" +
            "#887 @ 371,905: 17x21\n" +
            "#888 @ 323,79: 23x10\n" +
            "#889 @ 60,353: 20x21\n" +
            "#890 @ 576,776: 22x12\n" +
            "#891 @ 34,765: 15x11\n" +
            "#892 @ 663,599: 13x13\n" +
            "#893 @ 153,497: 4x16\n" +
            "#894 @ 737,199: 18x11\n" +
            "#895 @ 66,512: 13x24\n" +
            "#896 @ 614,57: 19x21\n" +
            "#897 @ 294,474: 12x21\n" +
            "#898 @ 176,171: 18x14\n" +
            "#899 @ 910,859: 29x28\n" +
            "#900 @ 949,399: 15x13\n" +
            "#901 @ 955,980: 10x11\n" +
            "#902 @ 513,151: 26x13\n" +
            "#903 @ 309,602: 18x21\n" +
            "#904 @ 893,860: 23x19\n" +
            "#905 @ 790,959: 14x10\n" +
            "#906 @ 965,117: 13x11\n" +
            "#907 @ 904,751: 12x26\n" +
            "#908 @ 148,390: 13x27\n" +
            "#909 @ 740,933: 19x27\n" +
            "#910 @ 908,745: 13x25\n" +
            "#911 @ 787,962: 12x25\n" +
            "#912 @ 752,842: 21x20\n" +
            "#913 @ 255,597: 12x22\n" +
            "#914 @ 92,40: 12x10\n" +
            "#915 @ 527,893: 18x13\n" +
            "#916 @ 913,447: 24x24\n" +
            "#917 @ 962,130: 26x13\n" +
            "#918 @ 879,858: 13x17\n" +
            "#919 @ 406,33: 21x20\n" +
            "#920 @ 688,66: 14x12\n" +
            "#921 @ 520,82: 28x21\n" +
            "#922 @ 29,532: 25x27\n" +
            "#923 @ 446,804: 27x14\n" +
            "#924 @ 391,716: 16x15\n" +
            "#925 @ 188,239: 28x14\n" +
            "#926 @ 327,802: 3x11\n" +
            "#927 @ 67,525: 16x14\n" +
            "#928 @ 952,96: 14x27\n" +
            "#929 @ 763,874: 19x15\n" +
            "#930 @ 246,111: 20x18\n" +
            "#931 @ 875,599: 20x15\n" +
            "#932 @ 388,799: 16x15\n" +
            "#933 @ 812,703: 12x11\n" +
            "#934 @ 803,704: 16x15\n" +
            "#935 @ 82,421: 23x26\n" +
            "#936 @ 832,322: 24x25\n" +
            "#937 @ 801,852: 18x13\n" +
            "#938 @ 101,869: 16x12\n" +
            "#939 @ 905,757: 14x28\n" +
            "#940 @ 94,616: 23x25\n" +
            "#941 @ 340,369: 12x16\n" +
            "#942 @ 745,49: 25x27\n" +
            "#943 @ 729,539: 26x15\n" +
            "#944 @ 762,148: 15x24\n" +
            "#945 @ 392,773: 17x16\n" +
            "#946 @ 495,224: 13x10\n" +
            "#947 @ 418,322: 3x13\n" +
            "#948 @ 63,836: 29x27\n" +
            "#949 @ 923,721: 18x29\n" +
            "#950 @ 107,325: 4x17\n" +
            "#951 @ 239,246: 14x10\n" +
            "#952 @ 20,60: 14x12\n" +
            "#953 @ 196,211: 26x15\n" +
            "#954 @ 209,217: 19x21\n" +
            "#955 @ 188,80: 26x29\n" +
            "#956 @ 427,208: 27x21\n" +
            "#957 @ 266,240: 20x22\n" +
            "#958 @ 693,940: 15x21\n" +
            "#959 @ 225,900: 11x12\n" +
            "#960 @ 783,847: 21x29\n" +
            "#961 @ 309,705: 17x15\n" +
            "#962 @ 715,322: 27x14\n" +
            "#963 @ 658,639: 17x17\n" +
            "#964 @ 303,591: 23x29\n" +
            "#965 @ 322,681: 19x24\n" +
            "#966 @ 959,212: 28x13\n" +
            "#967 @ 463,919: 11x13\n" +
            "#968 @ 871,197: 28x22\n" +
            "#969 @ 920,273: 23x23\n" +
            "#970 @ 541,829: 21x15\n" +
            "#971 @ 212,496: 13x16\n" +
            "#972 @ 945,19: 26x23\n" +
            "#973 @ 482,24: 20x23\n" +
            "#974 @ 749,518: 20x27\n" +
            "#975 @ 347,207: 27x10\n" +
            "#976 @ 460,345: 19x28\n" +
            "#977 @ 71,63: 17x26\n" +
            "#978 @ 508,968: 22x15\n" +
            "#979 @ 481,227: 20x21\n" +
            "#980 @ 902,390: 29x28\n" +
            "#981 @ 200,643: 13x29\n" +
            "#982 @ 283,514: 27x26\n" +
            "#983 @ 675,609: 24x23\n" +
            "#984 @ 599,95: 27x26\n" +
            "#985 @ 262,369: 10x17\n" +
            "#986 @ 113,252: 25x26\n" +
            "#987 @ 54,432: 12x26\n" +
            "#988 @ 669,845: 19x21\n" +
            "#989 @ 68,623: 24x25\n" +
            "#990 @ 965,122: 13x23\n" +
            "#991 @ 909,954: 11x21\n" +
            "#992 @ 441,807: 28x24\n" +
            "#993 @ 811,921: 10x13\n" +
            "#994 @ 891,494: 10x23\n" +
            "#995 @ 579,154: 26x18\n" +
            "#996 @ 148,411: 26x28\n" +
            "#997 @ 654,130: 25x11\n" +
            "#998 @ 233,577: 21x23\n" +
            "#999 @ 810,493: 15x15\n" +
            "#1000 @ 466,766: 24x15\n" +
            "#1001 @ 133,180: 16x16\n" +
            "#1002 @ 963,960: 26x19\n" +
            "#1003 @ 170,780: 14x29\n" +
            "#1004 @ 322,960: 21x22\n" +
            "#1005 @ 300,509: 23x17\n" +
            "#1006 @ 20,305: 26x23\n" +
            "#1007 @ 679,358: 21x26\n" +
            "#1008 @ 982,661: 14x21\n" +
            "#1009 @ 31,241: 18x28\n" +
            "#1010 @ 629,771: 23x20\n" +
            "#1011 @ 241,596: 11x16\n" +
            "#1012 @ 982,730: 11x25\n" +
            "#1013 @ 228,536: 20x22\n" +
            "#1014 @ 327,715: 15x11\n" +
            "#1015 @ 609,214: 19x21\n" +
            "#1016 @ 403,297: 10x15\n" +
            "#1017 @ 697,552: 27x10\n" +
            "#1018 @ 416,584: 23x13\n" +
            "#1019 @ 721,83: 25x18\n" +
            "#1020 @ 703,539: 27x17\n" +
            "#1021 @ 113,101: 28x21\n" +
            "#1022 @ 537,730: 18x19\n" +
            "#1023 @ 63,796: 22x14\n" +
            "#1024 @ 843,203: 13x22\n" +
            "#1025 @ 724,81: 20x23\n" +
            "#1026 @ 308,315: 21x17\n" +
            "#1027 @ 212,547: 19x25\n" +
            "#1028 @ 860,670: 12x20\n" +
            "#1029 @ 101,114: 17x25\n" +
            "#1030 @ 258,534: 11x22\n" +
            "#1031 @ 478,756: 18x12\n" +
            "#1032 @ 909,840: 3x5\n" +
            "#1033 @ 403,717: 22x21\n" +
            "#1034 @ 675,692: 26x22\n" +
            "#1035 @ 16,660: 28x16\n" +
            "#1036 @ 300,780: 18x29\n" +
            "#1037 @ 401,808: 27x25\n" +
            "#1038 @ 809,896: 20x28\n" +
            "#1039 @ 265,522: 19x11\n" +
            "#1040 @ 91,134: 26x13\n" +
            "#1041 @ 580,163: 19x20\n" +
            "#1042 @ 455,780: 10x20\n" +
            "#1043 @ 891,145: 20x21\n" +
            "#1044 @ 323,464: 18x26\n" +
            "#1045 @ 655,830: 13x24\n" +
            "#1046 @ 605,76: 13x18\n" +
            "#1047 @ 256,516: 15x23\n" +
            "#1048 @ 910,735: 12x19\n" +
            "#1049 @ 331,6: 28x14\n" +
            "#1050 @ 82,73: 22x10\n" +
            "#1051 @ 230,110: 13x24\n" +
            "#1052 @ 853,83: 15x12\n" +
            "#1053 @ 729,723: 17x22\n" +
            "#1054 @ 611,193: 11x11\n" +
            "#1055 @ 716,854: 27x24\n" +
            "#1056 @ 132,627: 28x21\n" +
            "#1057 @ 401,230: 26x20\n" +
            "#1058 @ 176,220: 13x10\n" +
            "#1059 @ 643,357: 11x28\n" +
            "#1060 @ 334,213: 18x12\n" +
            "#1061 @ 395,694: 15x13\n" +
            "#1062 @ 19,370: 17x25\n" +
            "#1063 @ 9,77: 13x25\n" +
            "#1064 @ 720,337: 16x21\n" +
            "#1065 @ 669,595: 24x12\n" +
            "#1066 @ 456,911: 11x10\n" +
            "#1067 @ 459,519: 14x23\n" +
            "#1068 @ 353,539: 28x10\n" +
            "#1069 @ 703,304: 16x29\n" +
            "#1070 @ 885,621: 11x11\n" +
            "#1071 @ 184,615: 20x20\n" +
            "#1072 @ 524,380: 19x15\n" +
            "#1073 @ 208,661: 19x15\n" +
            "#1074 @ 815,916: 18x28\n" +
            "#1075 @ 26,180: 10x10\n" +
            "#1076 @ 897,734: 27x19\n" +
            "#1077 @ 468,605: 27x13\n" +
            "#1078 @ 671,361: 14x27\n" +
            "#1079 @ 387,383: 12x10\n" +
            "#1080 @ 410,21: 13x15\n" +
            "#1081 @ 899,39: 26x26\n" +
            "#1082 @ 69,356: 13x26\n" +
            "#1083 @ 484,547: 24x20\n" +
            "#1084 @ 127,735: 24x18\n" +
            "#1085 @ 949,971: 23x25\n" +
            "#1086 @ 618,532: 29x10\n" +
            "#1087 @ 973,776: 26x17\n" +
            "#1088 @ 752,724: 20x16\n" +
            "#1089 @ 88,323: 29x22\n" +
            "#1090 @ 588,78: 24x24\n" +
            "#1091 @ 813,263: 11x28\n" +
            "#1092 @ 94,496: 8x13\n" +
            "#1093 @ 482,157: 20x16\n" +
            "#1094 @ 638,793: 14x13\n" +
            "#1095 @ 388,160: 12x13\n" +
            "#1096 @ 911,907: 24x23\n" +
            "#1097 @ 38,222: 27x23\n" +
            "#1098 @ 770,502: 23x10\n" +
            "#1099 @ 889,953: 20x24\n" +
            "#1100 @ 954,918: 15x12\n" +
            "#1101 @ 746,605: 15x18\n" +
            "#1102 @ 120,750: 19x19\n" +
            "#1103 @ 322,379: 22x11\n" +
            "#1104 @ 542,65: 13x12\n" +
            "#1105 @ 832,431: 10x23\n" +
            "#1106 @ 892,27: 13x29\n" +
            "#1107 @ 434,248: 20x17\n" +
            "#1108 @ 661,943: 11x26\n" +
            "#1109 @ 487,728: 29x15\n" +
            "#1110 @ 879,615: 27x13\n" +
            "#1111 @ 371,421: 11x24\n" +
            "#1112 @ 304,875: 16x17\n" +
            "#1113 @ 235,562: 10x28\n" +
            "#1114 @ 678,331: 17x18\n" +
            "#1115 @ 30,762: 24x11\n" +
            "#1116 @ 439,928: 25x28\n" +
            "#1117 @ 528,365: 12x25\n" +
            "#1118 @ 232,116: 15x15\n" +
            "#1119 @ 329,599: 22x17\n" +
            "#1120 @ 9,65: 28x25\n" +
            "#1121 @ 287,467: 27x27\n" +
            "#1122 @ 371,732: 27x12\n" +
            "#1123 @ 942,848: 12x17\n" +
            "#1124 @ 167,876: 23x19\n" +
            "#1125 @ 531,18: 28x18\n" +
            "#1126 @ 662,861: 10x12\n" +
            "#1127 @ 700,197: 28x18\n" +
            "#1128 @ 314,786: 13x13\n" +
            "#1129 @ 942,296: 15x15\n" +
            "#1130 @ 259,118: 15x25\n" +
            "#1131 @ 306,144: 12x22\n" +
            "#1132 @ 78,537: 15x23\n" +
            "#1133 @ 348,606: 14x23\n" +
            "#1134 @ 648,772: 13x10\n" +
            "#1135 @ 500,246: 18x15\n" +
            "#1136 @ 754,150: 20x15\n" +
            "#1137 @ 974,586: 10x29\n" +
            "#1138 @ 348,610: 16x18\n" +
            "#1139 @ 336,300: 11x12\n" +
            "#1140 @ 200,430: 11x15\n" +
            "#1141 @ 505,825: 16x22\n" +
            "#1142 @ 870,581: 19x15\n" +
            "#1143 @ 686,77: 25x29\n" +
            "#1144 @ 794,889: 23x11\n" +
            "#1145 @ 564,615: 27x10\n" +
            "#1146 @ 312,440: 22x19\n" +
            "#1147 @ 234,636: 13x16\n" +
            "#1148 @ 532,75: 19x26\n" +
            "#1149 @ 188,544: 29x12\n" +
            "#1150 @ 892,72: 17x13\n" +
            "#1151 @ 830,815: 23x15\n" +
            "#1152 @ 942,260: 23x10\n" +
            "#1153 @ 544,200: 16x14\n" +
            "#1154 @ 795,961: 22x12\n" +
            "#1155 @ 88,48: 26x15\n" +
            "#1156 @ 726,86: 13x21\n" +
            "#1157 @ 507,432: 22x26\n" +
            "#1158 @ 253,757: 28x11\n" +
            "#1159 @ 111,848: 19x26\n" +
            "#1160 @ 500,766: 22x21\n" +
            "#1161 @ 322,685: 15x24\n" +
            "#1162 @ 703,532: 25x11\n" +
            "#1163 @ 185,414: 28x12\n" +
            "#1164 @ 377,763: 25x15\n" +
            "#1165 @ 87,66: 17x13\n" +
            "#1166 @ 125,950: 17x10\n" +
            "#1167 @ 601,549: 24x20\n" +
            "#1168 @ 763,497: 28x13\n" +
            "#1169 @ 162,312: 20x18\n" +
            "#1170 @ 201,112: 7x5\n" +
            "#1171 @ 111,743: 25x13\n" +
            "#1172 @ 576,911: 22x11\n" +
            "#1173 @ 257,42: 13x6\n" +
            "#1174 @ 404,373: 20x25\n" +
            "#1175 @ 745,503: 21x24\n" +
            "#1176 @ 593,520: 23x24\n" +
            "#1177 @ 14,969: 19x18\n" +
            "#1178 @ 115,958: 19x13\n" +
            "#1179 @ 50,542: 10x24\n" +
            "#1180 @ 846,26: 11x14\n" +
            "#1181 @ 231,124: 13x10\n" +
            "#1182 @ 981,58: 15x17\n" +
            "#1183 @ 619,199: 12x20\n" +
            "#1184 @ 608,360: 19x14\n" +
            "#1185 @ 601,977: 15x10\n" +
            "#1186 @ 581,736: 21x19\n" +
            "#1187 @ 402,338: 22x27\n" +
            "#1188 @ 189,723: 14x10\n" +
            "#1189 @ 255,329: 13x25\n" +
            "#1190 @ 169,156: 15x20\n" +
            "#1191 @ 611,793: 16x21\n" +
            "#1192 @ 110,715: 16x25\n" +
            "#1193 @ 516,11: 22x24\n" +
            "#1194 @ 576,793: 19x11\n" +
            "#1195 @ 317,440: 10x24\n" +
            "#1196 @ 761,482: 18x12\n" +
            "#1197 @ 213,703: 16x29\n" +
            "#1198 @ 846,884: 27x22\n" +
            "#1199 @ 430,578: 13x27\n" +
            "#1200 @ 470,777: 15x27\n" +
            "#1201 @ 29,846: 18x24\n" +
            "#1202 @ 668,627: 27x13\n" +
            "#1203 @ 9,398: 10x27\n" +
            "#1204 @ 656,674: 22x27\n" +
            "#1205 @ 746,203: 19x13\n" +
            "#1206 @ 924,141: 18x15\n" +
            "#1207 @ 240,785: 16x19\n" +
            "#1208 @ 702,819: 12x19\n" +
            "#1209 @ 145,890: 24x24\n" +
            "#1210 @ 349,607: 14x21\n" +
            "#1211 @ 580,229: 20x15\n" +
            "#1212 @ 846,79: 10x19\n" +
            "#1213 @ 132,919: 22x23\n" +
            "#1214 @ 767,215: 27x15\n" +
            "#1215 @ 301,705: 27x24\n" +
            "#1216 @ 713,178: 26x24\n" +
            "#1217 @ 270,635: 12x10\n" +
            "#1218 @ 813,882: 25x13\n" +
            "#1219 @ 5,580: 24x24\n" +
            "#1220 @ 771,100: 11x14\n" +
            "#1221 @ 329,836: 28x28\n" +
            "#1222 @ 553,524: 18x19\n" +
            "#1223 @ 87,576: 14x20\n" +
            "#1224 @ 768,931: 9x14\n" +
            "#1225 @ 550,165: 14x14\n" +
            "#1226 @ 191,892: 28x17\n" +
            "#1227 @ 542,916: 12x16\n" +
            "#1228 @ 648,679: 27x13\n" +
            "#1229 @ 459,943: 25x19\n" +
            "#1230 @ 104,377: 18x25\n" +
            "#1231 @ 439,708: 14x10\n" +
            "#1232 @ 400,652: 27x27\n" +
            "#1233 @ 825,902: 23x17\n" +
            "#1234 @ 592,85: 28x19\n" +
            "#1235 @ 361,669: 19x16\n" +
            "#1236 @ 771,583: 10x20\n" +
            "#1237 @ 4,810: 22x10\n" +
            "#1238 @ 665,353: 28x21\n" +
            "#1239 @ 79,958: 12x17\n" +
            "#1240 @ 171,386: 15x29\n" +
            "#1241 @ 515,371: 21x10\n" +
            "#1242 @ 19,171: 16x22\n" +
            "#1243 @ 246,990: 12x10\n" +
            "#1244 @ 652,51: 11x29\n" +
            "#1245 @ 938,354: 26x21\n" +
            "#1246 @ 102,293: 19x18\n" +
            "#1247 @ 163,693: 16x26\n" +
            "#1248 @ 198,611: 18x19\n" +
            "#1249 @ 597,719: 14x24\n" +
            "#1250 @ 671,353: 16x20\n" +
            "#1251 @ 770,486: 26x15\n" +
            "#1252 @ 723,58: 15x20\n" +
            "#1253 @ 706,307: 11x18\n" +
            "#1254 @ 474,10: 16x15\n" +
            "#1255 @ 536,321: 15x14\n" +
            "#1256 @ 569,326: 23x18\n" +
            "#1257 @ 346,643: 29x27\n" +
            "#1258 @ 137,609: 25x13\n" +
            "#1259 @ 165,804: 12x19\n" +
            "#1260 @ 613,783: 17x22\n" +
            "#1261 @ 918,531: 20x16\n" +
            "#1262 @ 632,21: 17x24\n" +
            "#1263 @ 188,93: 18x17\n" +
            "#1264 @ 884,153: 15x25\n" +
            "#1265 @ 383,645: 16x24\n" +
            "#1266 @ 107,768: 20x10\n" +
            "#1267 @ 610,527: 11x17\n" +
            "#1268 @ 25,118: 15x17\n" +
            "#1269 @ 70,605: 19x27\n" +
            "#1270 @ 60,268: 14x12\n" +
            "#1271 @ 527,837: 27x17\n" +
            "#1272 @ 821,313: 19x14\n" +
            "#1273 @ 474,8: 21x12\n" +
            "#1274 @ 971,883: 26x16\n" +
            "#1275 @ 716,292: 20x25\n" +
            "#1276 @ 73,134: 20x15\n" +
            "#1277 @ 160,621: 18x16\n" +
            "#1278 @ 370,542: 14x14\n" +
            "#1279 @ 244,929: 29x23\n" +
            "#1280 @ 554,299: 29x28\n" +
            "#1281 @ 551,8: 12x22\n" +
            "#1282 @ 885,147: 13x27\n" +
            "#1283 @ 512,120: 29x14\n" +
            "#1284 @ 845,570: 18x19\n" +
            "#1285 @ 534,918: 17x24\n" +
            "#1286 @ 512,253: 17x10\n" +
            "#1287 @ 320,426: 21x21\n" +
            "#1288 @ 66,530: 24x12\n" +
            "#1289 @ 742,155: 29x13\n" +
            "#1290 @ 113,935: 27x21\n" +
            "#1291 @ 822,405: 23x14\n" +
            "#1292 @ 469,635: 27x22\n" +
            "#1293 @ 428,454: 29x11\n" +
            "#1294 @ 770,573: 27x20\n" +
            "#1295 @ 240,934: 29x28";
}
