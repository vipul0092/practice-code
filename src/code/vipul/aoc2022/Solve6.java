package code.vipul.aoc2022;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vgaur created on 06/12/22
 */
public class Solve6 {

    private static final String INPUT = "djhjvjggdzznllvvrvggscgscsrrffgvfvllfclcrchhwzhzqqlhqhffsdsmmcffnggcttdpttwpwttjvtjvtvqqctcwcmcsswvwzzlnzlnnvbnbdnngmmhchrcrqqhbhllbtllmppgjjtvjvdjvvpcpjcjjfrfzfzzdvzdvvswvvjzzbpzbzzddbndbbgjbjjvpjpjtjqtjqjcjmcjjrtjrjrqqvtvpvwpprhphrhdrddpdhhfsspddqnqwnntrtnrrthrhtrtwtdttmmnvmnmppswsqwqjqwqbqrqbqdbqdqgdqgqtgqqgzzhpzzwswvwmvwwvvrzznwzzbsbhbfhhvcvwvvrzrgzzfhzhhlthlhqhgqgttlmljmlmqqjddtqtctbblplddnqnzqnnzrzjrrqwrqqcfffbdfffspsqswstspsvppqmppdmppdvvfrfddqhqzqddtjddfqfrfllnjjcnnjzjmzmtmddbvdvzvbzzcjzzffdbdbsswshhrwrrfggpccszzzgdgvvlflwwdbbhqhffngffdfdmdgdhdmhdmdsspssctstdtdmdhhzvvcbbrqqmrmwwjmjqjmqjmjjjrmrmlmmbppmgpgttmptpspmmrttcddtjjspsfsqqbhbbzzgbzzznwzzlddmtdmdgmmlnljlvjjtgjjggmmnnvqnqzzfhzhttvbbprpmpmrmlrmllwmlwmlwwsjjlffbgfggqmggqgvglvvrpvrpvpnpphmhnmhhbbqjbbrrvfrfwflldffzwzccscqsqppctctddqbbmggmccdbdvbdvvpdpdbdsdjsjbbwpwcpcbcbmmzdmmvtvqvpvphhlblwwfmwmvvdhdtdwwlblglhhvfvwwqggrnrttpddtvvwqvqmqhhwnnghhbpbvbnvvdqqrqdqwqppmwpwhppnmnjmnjmjttvhhcgchcssrlrwllpdpndpdtptzppqqpvvtffcwffjppvnnjvvjnnwcwnnhlhjhsjsnjjzfjfsfhsssvttvfvsvpspppwswmswwqmwwzvwzzvtzvvwddqqzhhqpqjjwrwlrrbcbvvqllqjllvzvgvmvhmmsppwvpwphhjnndjjtpjtthzzvrrcwcrczzmqmsssvtstqtrrgtrtvtwtccbwcwrwbbdbgbmmcsmcssvjsjqqsnqqtvvbgbfbdffhjffvnnzpplqppzzfwfrfnfcncqccgjjcffhshrhgrhghvhphccqtcqtccjzccdnncggftgttrppnpptlltztqtjqqvfvqvdvmvmjjgqqrqgrqqcggdvvpcvcjvjnnrjjmbmlbmmqvmqmfmwmpwwnhwdtmvhqfwlbpzjplfhfntjgmvqmmjqpbngpvjvpgzpqwjjwhvjwwplrtjhzmzqmdrppgbrspmctlggmflbjzzfcvvdqlrtvqvwhcpjnmlvfgwrwwtblpqstddnqntnmwsbgjfrbdrlnvqdrnttshjmvpmncmggfdbnndwzmswmdvhmmwtgpfglrzzhwcsgvhnnrrhmnhftvvqfdfrsphzbslgscmwsnrwbvqphhswvpvsbbstvnndclhfhdctlvwrmdgzfcfmjmznqvvqrddmdlqznvcsqsgnpcqqhbdwqntcnqljstqvrzhgvzqdltpwmnpvjmqrpjsfhqvhchjnwjnqpdqbdjqdpqsqhbwwmhfthzbrsjnhncpbjrhgqlzmrzlnvrrfvlrmflcqfmqjzjwscrflgzwtbchfrnvsrrtncvhjbnnmlmmfdjcbmbsmgdtwzwcwnthfbsnrgdfwqjncqsdmfnfqgtcwrhjprlnhvrnpmnnhlwstvqjrsprqhjzszzgfznmgwjqglvfrrwpdbptdrnbbwbzbcbhbtcchmfsgmvnmrbdqhqgmvtfmvpgvjzjpgjbdhcfrfhprgdzrprccnhbmzdfjsgldlgpgdrfhbhtmhdttdsbndgbdfccqhhwhqfmlsfhsbbbmdncrwzcnrdvcmhllfwtrgjpgngzwptnqtggtcjwrptffmsrgdpctsdjtpssngsdqwfsbhdbcqvbdrzlhzlsbbzhqthzhcwsftlhrmhgpfzljgcphjjvhpqjzsfnrztwrhlnlbmgcgmstrbbwclpvdtdpclzlhmmpmmpmppnwjglhwppprlbzbvwqwmpgtvvpgdthnwbtblwpwgvmbcbjwjbczlcmzfwzbqvzsvgcmspvrsblldscqlgghdwzbvhhvgcfwgnqwlngclbjfwrpwtdjvqmzwwjztwdjplhzpzfslbbvfdsnpggwcttzwdlzgqgmrnpnclhrlngtwcwblzdjmpgqvzsvsdmzdwlgcdlccnnlrcvtrvspcsmgmzzvwnlzwtznwtqtdjcnhwrqhqrmvqqhrpdtnsmfrlcgpjcnddsqzcppgrnhvwsdbjvvtmvbjdncpdnmzfswmtvzfbdpqfjvwvqlhptnpdfdnlwfrgstpvvmhsqfgggdrsfgldfzbcjzhqzvfwmzccwjrslhjwlbmrpqgzdfnfbhsmdpzwtqnqldtqvshvlvmlnnmqrqbpwvnhqhtcbfclhrcqlqzhsqplsnbczvrbzqwlfwjdtmstzdbswtrvlpzzlrfvgdmldbwcttztrvsgzjwhhpcrvtgzfzppdlrdwswbnjfqqpqfbcqlzdmjsgjtzmvhdzspwlqpdjnccmbtdhnnhfvwqclbzzgglfgmvvgrccdsbwfmpvqwqrhmdzfhhhgbgjgwmnzmnggfrpspchvzpcmcpsbzgldmgqjqqdcjpwwncwrwjbhgzdbbcmbzbbtvprsjrhfwgsppdrrlzvnmtmwrmmrhtlndvsvjvgqmmttbbnpdhnjhwgrvlrdtpbrtwpwvvpslcqnvnrlhpvgdwnrzjmhwmgvpndtrjlzqpfzfbrsgbzjjqcfgsfwchblzstdflblngtzbrzrrvsczqvfhjjdlffrghgqvqfdtstqlnzllsrnnrtvrzdphbhdfpmhlfncqbdtzjqqcfbzpvgzdcsvvbfdvqrfrncbrwmpdmhnlqdscwnvldzblpzfqcvnbzmmtbmwjbczsjvzmfthfpvjcpwftqcbgjwflfrbrggwnvwndtncljfrdfwqwhfbctpjghfvnjnntnrgbfbmhplgmpfvmgvfqjslgnnrnlgztlstpcjwtlhmwlljcfmptfwsphnlsrjwmgtghgqmsvwvqsmblwpdftbrwjcdlzjmjblghszznqhsnqrcmtccgdwrrlsmwswvrjltqmwsdwvpnzltllhrsdvmrntdhtwwbgrqmrffnqbqrczvzchbgmzwtjtfzwntsnlbwbgrlvqjsqmdnwjqlwrdpnfpggzrjvtrhqdbmmbtfmmblgwtrqccqbjnljqflhgtphvrgrgghgrpbgfgdztsmfwrfflsqmrwbfjwsmpfrnbqjwnwdqwcwzpwbsmngjwfmbwdmnprdjnjbmqgfcbvtcvcthpmnmvvzdzgqqbhtjqfcdvhfzwqgfsbtvnwbzpmmtswfntjjppsswgbfrjbrstltdgbmclmbfvlslghbhnqqbdlzgtctgsfnwvbpzbvnwfbjmbfqcpqqvgrzwcbrwzdbdsjsslcjlmtprntpsdmqldzwqlqztwqtqfqzmrnzbtpqlfnsdwfdgggfvmqmrdqmnffnzcwfzsrqfpvrmsfsrbnpbhnqvdglvglllpggpmwmngrhzwgpdlzrbsvjtqmshhnlzwwftdtqwrqwgbbnczqcwmsvcljqlscftwflhwwhgnqwztfchdzsllrzbhbqwcfztjnqtdmsfnlzlcwzfmtlcgwclzfhhldgrnfjvzthzqzmzvwcrnhpdcwswpddsbwtznwlcwsnfqnqwnntngplwnfgwrcnpvgffwrcrszzdbfvzjmrmlrjwcvdvbglgncjwcnnpdfnwsrzsvzgnjrlqmwhvtdgmpbqmjthmhhmzjhpvnbvrqnlspdbcgshwlnvwpvrbcmvbvcsdmgwtmsthqtcfmllsfwvqcrbmdgqtzjwrg";

    public static void solve() {
        solveInternal(INPUT, 4);
    }

    public static void solvePart2() {
        solveInternal(INPUT, 14);
    }

    private static void solveInternal(String in, int limit) {
        Map<Character, Integer> sets = new LinkedHashMap<>();
        for (int i = 0; i < in.length(); i++) {
            char ch = in.charAt(i);

            sets.putIfAbsent(ch, 0);
            sets.put(ch, sets.get(ch) + 1);
            if (i >= limit) {
                char remove = in.charAt(i - limit);
                if (sets.get(remove) > 1) {
                    sets.put(remove, sets.get(remove) - 1);
                } else {
                    sets.remove(remove);
                }

                if (sets.size() == limit) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }
}
