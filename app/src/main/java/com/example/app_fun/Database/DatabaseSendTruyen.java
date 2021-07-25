package com.example.app_fun.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseSendTruyen extends SQLiteOpenHelper {
    // cơ sở dũ liệu

    // tên bảng database
    private static String DATABASE_NAME = "doctruyen";

    // biến bảng thài khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    //version
    private static int VERSION = 1;
    // biến bảng độc truyện
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    // context
    private Context context;

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT," +
           " "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";

    // insert truyện
    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Rùa và Thỏ','Phần 1:\n" +
            "\n" +
            "Ngày xửa ngày xưa, có một con Rùa và một con Thỏ sống trong một khu rừng xinh đẹp và yên tĩnh. Ngày ngày chúng vui chơi với nhau như hai người bạn thân. Một hôm, Thỏ và Rùa cãi nhau xem ai nhanh hơn. Rồi chúng quyết định giải quyết việc tranh luận bằng một cuộc thi chạy đua. Thỏ và Rùa đồng ý lộ trình và bắt đầu cuộc đua. Thỏ xuất phát nhanh như tên bắn và chạy thục mạng rất nhanh, khi thấy rằng mình đã khá xa Rùa, Thỏ nghĩ nên nghỉ cho đỡ mệt dưới một bóng cây xum xuê lá bên vệ đường. Vì quá tự tin vào khả năng giành chiến thắng của mình, Thỏ ngồi dưới bóng cây và nhanh chóng ngủ thiếp đi. Rùa chạy mãi rồi cũng đến nơi, thấy Thỏ đang ngủ ngon giấc Rùa từ từ vượt qua Thỏ và về đích trước Thỏ. Khi Thỏ thức dậy thì rùa đã đến đích và trở thành người chiến thắng. Lúc này Thỏ biết mình đã thua cuộc vì quá tự tin vào khả năng của mình, còn Rùa chiến thắng vì kiên trì bám đuổi mục tiêu và làm việc hết sức trong khả năng của mình, cộng với một chút may mắn và giành chiến thắng.\n" +
            "\n" +
            "Ý nghĩa câu chuyện phần 1: truyện giáo dục đức tính kiên trì, siêng năng, nhẫn nại. Những người nhanh nhẹn nhưng cẩu thả trong suy nghĩ cuối cùng cũng sẽ bị đánh bại bởi người kiên nhẫn, siêng năng dù họ chậm hơn rất nhiều.\n" +
            "\n" +
            "Phần 2:\n" +
            "\n" +
            "Thỏ vô cùng thất vọng vì để thua Rùa, nó nhận ra rằng nó thua chính vì quá tự tin, bất cẩn và thiếu kỷ luật. Nếu nó không xem mọi thứ quá dễ dàng và chắc thắng thì rùa không thể có cơ hội hạ được nó. Vì thế, Thỏ quyết định thách thức Rùa bằng một cuộc đua mới. Rùa đồng ý. Lần này, Thỏ chạy với tất cả sức lực của nó và chạy một mạch về đích. Nó bỏ xa Rùa đến mấy dặm đường.\n"+
            "Ý nghĩa câu chuyện phần 2: Biết sai và sửa sai là một đức tính tốt, đó chính là lý do giúp anh chàng thỏ giành được chiến thắng ở cuộc đua thứ 2. Mẹ hãy giải thích cho bé hiểu rằng trong công việc hàng ngày giữa một người chậm, cẩn thận và đáng tin cậy với một người nhanh nhẹn, đáng tin cậy, chắc chắn người nhanh nhẹn sẽ được trọng dụng hơn nhiều và họ sẽ tiến xa hơn trong học tập, cũng như trong cuộc sống. Cha mẹ hãy giúp bé hiểu rõ thông điệp chậm và chắc là điều tốt, nhưng nhanh và đáng tin cậy sẽ tốt hơn rất nhiều.','https://toplist.vn/images/800px/rua-va-tho-230179.jpg',1)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Củ cải trắng','Mùa đông đã đến rồi trời lạnh buốt, Thỏ con không có gì để ăn cả. Thỏ con mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Thỏ con reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Thỏ con đói bụng, muốn ăn lắm rồi. Nhưng Thỏ lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Dê con cũng không có cái gì để ăn đâu. Mình phải mang cho Dê con một củ mới được.\n" +
            "\n" +
            "Thế là Thỏ con đi sang nhà bạn Dê nhưng Dê con không có nhà nên Thỏ đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Dê con đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Dê thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Hươu con không có cái gì để ăn rồi, mình phải mang cho Hươu con mới được.\n" +
            "\n" +
            "Dê con đến nhà Hươu nhưng Hươu lại đi vắng, Dê con bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Hươu về nhà, thấy củ cải ở trên bàn, Hươu ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Thỏ con cũng không có gì ăn đâu. Mình phải mang sang cho Thỏ mới được.\n" +
            "\n" +
            "Khi Hươu đến thì Thỏ con đang ngủ rất say. Khi tỉnh dậy Thỏ lại thấy trên bàn mình xuất hiện một củ cải trắng.Thỏ vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Hươu ơi, bạn Dê ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg',1)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'Dê đen và dê trắng','Dê đen và Dê trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +
            "Một hôm, Dê trắng đi tìm cái ăn và uống nước suối như mọi khi. Dê đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra. Sói quát hỏi:\n" +
            "\n" +
            "- Dê kia! Mi đi đâu?\n" +
            "\n" +
            "Dê trắng sợ rúm cả người, lắp bắp:\n" +
            "\n" +
            "– Dạ, dạ, tôi đi tìm… tìm cỏ non và…và uống nước suối ạ!\n" +
            "\n" +
            "Sói lại quát hỏi:\n" +
            "\n" +
            "– Mi có gì ở chân?\n" +
            "\n" +
            "– Dạ, dạ, chân của tôi có móng ạ…ạ!\n" +
            "\n" +
            "– Trên đầu mi có gì?\n" +
            "\n" +
            "– Dạ, dạ, trên đầu tôi có đôi sừng mới nhú…\n" +
            "\n" +
            "Sói càng quát to hơn:\n" +
            "\n" +
            "– Trái tim mi thế nào?\n" +
            "\n" +
            "– Ôi, ôi, trái…trái tim tôi đang run sợ…sợ…\n" +
            "\n" +
            "– Hahaha…\n" +
            "\n" +
            "Sói cười vang rồi ăn thịt chú Dê trắng tội nghiệp\n" +
            "\n" +
            "Dê đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:\n" +
            "\n" +
            "– Dê kia, mi đi đâu?\n" +
            "\n" +
            "Dê đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:\n" +
            "\n" +
            "– Ta đi tìm kẻ nào thích gây sự đây!\n" +
            "\n" +
            "Sói bị bất ngờ, nó hỏi tiếp:\n" +
            "\n" +
            "– Thế dưới chân mi có gì?\n" +
            "\n" +
            "– Chân thép của ta có móng bằng đồng.\n" +
            "\n" +
            "– Thế…thế…trên đầu mi có gì?\n" +
            "\n" +
            "– Trên đầu của ta có đôi sừng bằng kim cương!\n" +
            "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:\n" +
            "\n" +
            "– Mi…mi…trái tim mi thế nào?\n" +
            "\n" +
            "Dê đen dõng dạc trả lời:\n" +
            "\n" +
            "– Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây.\n" +
            "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Qua câu chuyện ngụ ngôn trên, bạn có thể truyền tải nhiều thông điệp khác nhau cho bé hiểu. Chẳng hạn như biết cách ứng xử trước các tình huống khó, nguy hiểm, lạc quan và bản lĩnh để xử lý vấn đề.','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg',1)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Chú bé chăn cừu','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "\n" +
            "– Sói ! Sói !\n" +
            "\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg',1)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'Cậu bé chăn cừu và cây đa cổ thụ','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg',1)";
    private String SQLQuery9 = "INSERT INTO truyen VALUES (null,'Chuyện cô Mây','Trên trời có một đám mây xinh đẹp, khi thì cô mặc áo trắng như bong, khi thì cô mặc áo xanh biếc, lúc thì cô lại đổi áo màu hồng tươi. Cô Mây cứ suốt ngày nhởn nhơ bay lượn, lúc lướt trên đỉnh núi, ngọn đồi, lúc bay trên biển cả mênh mông, lúc vờn đồng quê bát ngát. Nhưng bay mãi một mình cũng buồn vì chẳng có ai chơi với cô. Bác Mặt trời bận tỏa ánh nắng cho người phơi thóc. Cô Mặt trăng bận rãi ánh vàng cho các em bé vui chơi. May thay cô gặp Chị Gió. Cô gọi: \n" +
            "\n" +
            "\n" +
            "– Chị Gió ơi?\n" +
            "Chị Gió đáp: " +
            "– Chị đang bận rủ các bạn mây các nơi về làm mưa đây. Em có muốn làm mưa không?\n" +
            "– Làm mưa để làm gì hả chị?\n" +
            "– Làm mưa để cho cây cối tốt tươi, cho lúa to bông, cho khoai to củ.\n" +
            " - Thế làm mưa có dễ không chị Gió? \n" +
            " - Làm mưa cũng dễ thôi nhưng mà phải mệt, phải nhịn mặc áo đẹp, phải chịu lạnh rồi tan thành mưa, rơi xuống ruộng đồng. \n" +
            " - Thế không được làm mây nữa ư? \n" +
            " - Không, nhưng lại được làm nước chảy. Nước chảy có ích cho người. Thế em có thích làm nước chảy không? \n" +
            " Mây gật đầu \n " +


            "Chị cho em đi làm nước chảy với. Nhởn nhơ bay lượn một mình mãi em cũng chán lắm. Em muốn làm việc có ích cho con người cơ.\n" +
            "\n" +
            "Chị Gió thổi mạnh, đưa mây đi rất nhanh.\n" +
            "\n" +
            "Càng đi, càng bay sà xuống thấp, mây các nơi cùng kéo về đông nghịt, màu áo xám làm tối cả một vùng trời. Ai nấy đều nhanh nhẹn, vội vàng kéo nhau sà xuống thấp. Bỗng cô nhìn thấy một đoàn trẻ bé đang chơi trong vườn hoa, đàn trẻ nhảy nhót tung tăng ngẩn mặt lên trời mà hát:\n" +
            "Cầu trời mưa xuống\n" +
            "Lấy nước tôi uống\n" +
            "Lấy ruộng tôi cày\n" +
            "–Lấy bát cơm đầy\n" +
            " Lấy rơm đun bếp. \n" +
            "Cây, lá, cỏ, hoa thấy mây xám bay ngang cũng ngẩng đầu lên rì rào nói:\n" +
            "Mưa rơi xuống đây\n" +
            "Cho tốt cỏ cây\n" +
            "Cho tươi hoa lá\n" +
            "Nhớ mong mưa quá\n" +
            "Mưa ơi! Mưa ơi!\n" +
            "Vừa lúc đó cơn lạnh ùa đến. Đám mây xám rùng mình tan thành những giọt nước thi nhau tưới xuống đất rào rào. Đoàn trẻ dắt nhau chạy trốn dưới mái hiên. Cỏ, cây, hoa, lá tươi tỉnh mỉm cười đón mừng những giọt nước trong vắt đáng yêu. \n" +
            "hế là cô Mây trên trời cao đã hóa thành dòng nước chảy tràn khắp ao hồ, đồng ruộng, sông ngòi. Vài hôm nữa bác Mặt trời chiếu xuống nước bốc thành hơi. Chị Gió lại đưa nước lên trở thành Mây. \n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Dạy trẻ biết tiết kiệm nước, bảo vệ môi trường thiên nhiên','https://toplist.vn/images/800px/chuyen-co-may-230185.jpg', 1 )";

    private String SQLQuery10 = "INSERT INTO truyen VALUES (null,'Cậu bé mũi dài',' Ngày xưa có một cậu bé có cái mũi rất dài,vì thế mọi người gọi cậu là: “Bé Mũi dài”\n" +
            "Một buổi sáng đẹp trời, tiếng gió vi vu thổi, tiếng chim họa mi hót véo von. Bé Mũi dài nhìn thấy vườn hoa với muôn vàn bông hoa đua sắc khác nhau: hoa hướng dương vàng rực, hoa hồng, hoa cẩm chướng đỏ tươi.\n" +
            "Chú bé nhìn thấy một cây táo sai trĩu quả,những quả táo chín đỏ, thơm nức. Chú vội vàng leo lên cây để hái nhưng… chú không trèo lên được vì vướng phải cái mũi dài của mình. Bực quá chú nói: “Ước gì cái mũi của tôi biến mất, tôi chẳng cần cái mũi, tôi chỉ cần cái miệng để ăn đủ thứ thơm ngon trên đời, cũng chẳng cần có tai làm gì cả”.\n" +
            "Lúc đó chú Ong, cô Họa Mi đứng gần đó, thấy vậy đều ngạc nhiên nói: \n" +
            "\n" +
            "– Tại sao bạn không cần mũi? Đối với tôi mũi rất cần, có mũi tôi mới có thể thở được, ngửi và phân biệt được các mùi thơm khác nhau của các loài hoa.\n" +
            "\n" +
            "Lúc đó chim Họa Mi bay đến chỗ Mũi dài nói:\n" +
            "\n" +
            "– Nếu bạn không có tai thì làm sao nghe được tiếng hót của tôi và những âm thanh kỳ diệu xung quanh.\n" +
            "\n" +
            "Các cô hoa cũng rung rinh nói:\n" +
            "\n" +
            "– Nếu bạn không có mắt bạn có nhìn thấy những màu hoa rực rỡ của chúng tôi không?\n" +
            "Bé Mũi dài nghe xong hốt hoảng thấy mình không thể thiếu chúng được. Từ đó cậu luôn giữ gìn vệ sinh cơ thể, giữ gìn đôi mắt, cái mũi… không bao giờ có ý định vứt chúng đi nữa.\n \n" +
            "Ý nghĩa câu chuyện: Dạy trẻ biết tất cả các bộ phận đều cần thiết cho cơ thể, chúng ta phải bảo vệ và giữ gìn vệ sinh cơ thể chúng ta sạch sẽ.','https://toplist.vn/images/800px/cau-be-mui-dai-230186.jpg',1)";

    private String SQLQuery11 = "INSERT INTO truyen VALUES (null,'Chú chim nhỏ lười biếng','Có một chú chim nhỏ được bố mẹ cưng chiều nên rất lười biếng. Hàng ngày, ngoài việc ăn chơi và ngủ, chú chẳng làm gì cả. Một hôm các bạn chim rủ rê:\n" +
            "\n" +
            "– Chim nhỏ ơi, hãy học bay cùng với tụi mình đi.\n" +
            "\n" +
            "– Học bay làm gì? Tớ có bố mẹ chăm sóc, bảo vệ rồi nên không cần học nữa đâu.\n" +
            "\n" +
            "Nói rồi chim nhỏ bỏ đi chỗ khác, lấy bánh trái ra ăn. Các bạn chim chỉ biết lắc đầu rồi kéo nhau đi học bay. Chim nhỏ vừa thưởng thức đồ ăn vừa lầm bầm:\n" +
            "\n" +
            "– Mình không ngốc khi có đồ ăn ngon mà không ăn, lại đi học những thứ vô bổ.\n" +
            "\n" +
            "Một tháng qua đi, các bạn chim đều đã biết bay. Chúng rủ nhau đến thăm chim nhỏ và ngạc nhiên khi thấy nó rất béo ú. Chim nhỏ thì chẳng quan tâm gì đến các bạn mà quay lưng lại tiếp tục ngủ.\n" +
            "\n" +
            "Thấy vậy, các bạn chim lần lượt bay đi. Lúc đó, một con rắn núp trong tán cây hiện ra, thè cái lưỡi gớm ghiếc và bò từ từ đến chỗ chim nhỏ đang nằm.\n" +
            "\n" +
            "Chim nhỏ vẫn đang ngủ say nên không biết nguy hiểm đang rình rập mình. May sao, các bạn chim đang bay lượn gần đó đã nhìn thấy cảnh tượng này bèn sà nhanh xuống la toáng lên, báo động cho chim nhỏ:\n" +
            "\n" +
            "– Chim nhỏ dậy đi, mau bay đi thôi, có rắn kìa.\n" +
            "\n" +
            "Chim nhỏ giật mình tỉnh dậy, định vỗ cánh bay, nhưng do không biết bay nên đã rơi từ trên cây xuống. Các bạn của chim nhỏ nhanh trí cắp hai cái cánh của nó rồi bay đến nơi an toàn. Bị mất con mồi, rắn vô cùng tức giận.\n" +
            "\n" +
            "Sau khi hoàn hồn, chim nhỏ rất cảm kích ơn cứu mạng của các bạn, nhưng chợt nghĩ tới thái độ lúc trước của mình, nó đỏ mặt hổ thẹn, nói lí nhí:\n" +
            "\n" +
            "– Các cậu cho tớ xin lỗi nha. Cảm ơn các cậu đã cứu tớ. Tớ biết lỗi rồi, tớ sẽ học bay như các cậu, các cậu dạy cho tớ bay nhé.\n" +
            "\n" +
            "Các bạn chim đồng thanh:\n" +
            "\n" +
            "– Tất nhiên là được, nhưng mà…\n" +
            "\n" +
            "– Nhưng mà sao? – Chim nhỏ hỏi.\n" +
            "– Cậu phải giảm béo đã, nếu không thì với cái bụng tròn vo, cậu làm sao bay? \n" +
            "\n" +
            "Cả bọn cùng cười vui vẻ, một bạn chim tiếp lời:\n" +
            " – Bọn tớ đùa thôi, tụi mình cùng tập cho chim nhỏ bay nào. \n"+
            "Vậy là chim nhỏ đã bỏ thói quen lười biếng của mình, tập bay cùng các bạn. \n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Tuy các bé còn nhỏ, nhưng các bé phải học hành chăm chỉ để có đủ kiến thức và có thể tự lập về sau nhé, vì bố mẹ không thể nuôi bé suốt đời được, chúng ta cũng không thể không có bạn bè, vì thế bé không nên đối xử không tốt với bạn mà hãy cùng nhau học tập những điều bổ ích nhé!','https://toplist.vn/images/800px/chu-chim-nho-luoi-bieng-230187.jpg',1)";
    private String SQLQuery = "INSERT INTO truyen VALUES (null,'Cây táo thần','Ngày xửa, ngày xưa đã lâu lắm rồi, có một cây táo thần mọc ở ngoại ô thành phố. Hàng ngày bọn trẻ thường đến chơi ở đó, nô đùa xung quanh cây táo và bứt những quả táo ngon trên cành để chia nhau ăn.\n" +
            "\n" +
            "Một hôm có một cậu bé không biết từ đâu đến, cậu bé cau mày nói với bọn trẻ:\n" +
            "\n" +
            "- Này chúng mày, vườn này là của tao, tao đã mua từ trước. Cây táo này là của tao, chúng mày đi chỗ khác chơi, cấm không được đến đây nữa.\n" +
            "\n" +
            "Nghe vậy, bọn trẻ rất buồn, tất cả cúi đầu lặng lẽ ra về, chỉ còn cậu bé hống hách ở lại.\n" +
            "\n" +
            "Cây táo biết tất cả mọi chuyện, bằng phép lạ nó làm cho cậu bé ngủ thiếp đi dưới gốc cây và cũng bằng phép lạ nó làm cho cậu bé nằm mơ. Cậu bé mơ thấy trên thân cây táo có một cái hốc rất lớn. Cậu cảm thấy bụng đói cồn cào. Cậu bé trèo lên cây và định bứt táo để ăn. Nhưng mỗi khi tay cậu bé chạm vào một quả táo thì cành táo lại quay đi chỗ khác và quả táo lại rơi vào cái hốc to tướng ở thân cây. Cứ như vậy cho đến khi tất cả trên cành rơi vào hết cái hốc, chỉ còn trơ lại một quả trên cành.\n" +
            "\n" +
            "Cậu bé ngồi dưới gốc cây và khóc. Đến lúc đó cây táo mới cất tiếng hỏi:\n" +
            "\n" +
            "- Tại sao cháu khóc?\n" +
            "\n" +
            "Cậu bé mếu máo trả lời:- Ông ích kỉ quá. Ông ăn hết cả táo, ông không cho cháu một quả nào. Cháu đói lắm rồi ông ạ.\n" +
            "\n" +
            "Cây táo cười và nói:\n" +
            "\n" +
            "- Cháu có nhớ là cháu đã đuổi hết tất cả các bạn không? Các bạn cũng muốn ăn táo của ông nhưng cháu không cho các bạn một quả nào, như vậy cháu có ích kỉ không?\n" +
            "\n" +
            "Cậu bé nhớ lại lúc các bạn buồn rầu ra về, cậu bé thấy ân hận vô cùng, cậu bé ngước nhìn cây táo và nói:\n" +
            "\n" +
            "- Vâng cháu biết lỗi rồi!\n" +
            "\n" +
            "Cây táo cất tiếng cười vang, rung cả cây, làm cho quả táo còn lại cũng rơi trúng đầu cậu bé, cậu bé giật mình tỉnh giấc. Cậu bé ngơ ngác nhìn xung quanh, cậu bé thấy mình đang nằm dưới gốc cây. Cái hố to tướng trên cây táo biến mất. Cây táo vẫn đứng yên lặng và trên cây vẫn sai trĩu quả.\n" +
            "\n" +
            "Cậu bé chồm dậy, nhớ đến giấc mơ, cậu vội chạy đi gọi các bạn:\n" +
            "\n" +
            "- Này các bạn ơi! Quay lại đây chơi đi. Mình xin lỗi các bạn vì đã đuổi các bạn đi.\n" +
            "\n" +
            "Thế là tất cả cùng nhau chạy ra vườn. Cậu tự mình trèo lên bứt những quả chín ném xuống cho các bạn. Các bạn lại cười đùa vui vẻ. Cậu bé chợt hiểu rằng điều hạnh phúc nhất trên trái đất này là cùng chia sẻ niềm vui với mọi người.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Chỉ khi biết chia sẻ những thứ mình có với mọi người chúng ta mới có được niềm vui và hạnh phúc thực sự.','https://toplist.vn/images/800px/cay-tao-than-230189.jpg',1)";
    private String SQLQuery2 = "INSERT INTO truyen VALUES (null,'Con cừu đen kêu be be','Ngày xửa ngày xưa, có một con cừu đen sống trong một ngôi nhà nhỏ. Cứ đến mùa xuân, con cừu đen lại tự cạo sạch lông của mình và đem ra chợ bán cho những người muốn làm quần áo ấm.\n" +
            "\n" +
            "Một năm nọ, con cừu đen nhận thấy rằng dường như không ai còn chuộng lông cừu đen nữa. Do đó, số lông cừu mà nó còn lại khá nhiều. Dù vậy, nó không muốn lãng phí số lông này nên đã quyết tâm bán tiếp. Hôm đó, chẳng có ai muốn mua lông của nó cả nên con cừu đen mang số lông ấy về nhà. Ngày hôm sau, nó lại mang ra bán tiếp nhưng mọi chuyện cứ diễn ra y như hôm trước. Và hôm sau, hôm sau nữa cũng y như vậy.\n" +
            "\n" +
            "Một ngày nọ, khi con cừu đen đang ngồi buồn rầu với số lông của mình, có một cậu bé chạy lại và hỏi nó có bán số lông này không. Nghe hỏi, con cừu đen vô cùng mừng rỡ và nói có. Cậu bé chạy đến chỗ bố mẹ mình thông báo có chỗ bán lông cừu. Họ cùng nhau đến chỗ con cừu và ngỏ ý muốn mua hết toàn bộ số lông. Họ cho biết mình đến từ ngôi làng kế bên và đã tìm kiếm rất nhiều nơi để tìm mua lông cừu đen nhưng không có chỗ nào bán cả.\n" +
            "\n" +
            "Ngày hôm ấy, con cừu trở về nhà và cảm thấy vô cùng hạnh phúc khi những cố gắng của nó đã được đền đáp xứng đáng. \n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Hãy cố gắng, kiên trì và không bao giờ bỏ cuộc, có ngày con sẽ thành công.\n','https://toplist.vn/images/800px/con-cuu-den-keu-be-be-230190.jpg',1)";

    private String SQLQuery3 = "INSERT INTO truyen VALUES (null,'Ngỗng và rùa','Hai vợ chồng ngỗng và rùa là những người bạn tốt. Mùa hè đến, trời bắt đầu nóng lên khiến hồ nước dần cạn kiệt. Vì vậy, chúng lên kế hoạch đi tìm một chỗ ở mới. Tuy nhiên, rùa không thể bay được nên ngỗng quyết định mang rùa theo bằng cách vợ chồng ngỗng cùng ngậm một cái cây bay hai bên, ở giữa chú rùa cũng ngậm cái cây đó. Điều duy nhất mà rùa cần phải nhớ là không được mở miệng ra khi bay.\n" +
            "\n" +
            "Chúng bắt đầu cuộc hành trình của mình. Mọi người đều ngạc nhiên khi nhìn thấy chúng và bắt đầu la hét. Đột nhiên có ai đó cười lên: “Nhìn kìa, một con rùa bay”. Con rùa tức giận và đáp lại: “Tại sao cười tớ?”. Và tất nhiên, con rùa đã rớt xuống đất. May mắn thay, rùa rơi ngay vào một lùm cây nên không bị thương. Lúc này, rùa rất ân hận: “Giá như mình đừng lên tiếng, giờ này mình đã vui chơi cùng vợ chồng ngỗng rồi”.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Đừng nói chuyện khi không cần thiết.\n','https://toplist.vn/images/800px/ngong-va-rua-230191.jpg',1)";

    private String SQLQuery12 = "INSERT INTO truyen VALUES (null,'Dê và Cáo','Trong một khu rừng nọ, có một con sư tử rất hung bạo, sống trong một cái hang lớn và chứa nhiều thức ăn. Một hôm, con sư tử đi ra khỏi hang. Nhân lúc đó, một con cáo đã lẻn vào và ăn tất cả thức ăn có trong hang.\n" +
            "\n" +
            "Con cáo nghĩ: “Ước gì ngày nào mình cũng được ăn uống no say như thế này”.\n" +
            "\n" +
            "Sau khi đánh chén no nê, cáo đi dạo xung quanh và tận hưởng cảm giác lâng lâng khi mới ăn một bữa ngon lành. Đột nhiên, con cáo bất ngờ té xuống. Khi hoàn hồn lại, con cáo phát hiện ra rằng mình đã rơi xuống một cái giếng sâu nhưng không có nhiều nước.\n" +
            "\n" +
            "Lúc đầu, cáo rất tức giận với bản thân và tự trách tại sao mình lại không cẩn thận như vậy. Sau đó, nó cố gắng leo ra ngoài nhưng không thành công. Bỗng nhiên, cáo nghe thấy một giọng nói từ trên vọng xuống: “Cậu đang làm gì ở đó vậy?”. Cáo ngước lên nhìn và nhận ra rằng đó là con dê. Mừng quá, cáo nói: “Cậu biết không, tớ ở làng kế bên nhưng đang gặp hạn hán. Do đó, tớ phải nhảy xuống đây để lấy nước uống”.\n" +
            "\n" +
            "Nghe vậy, dê nhảy xuống giếng ngay lập tức. Lợi dụng điều đó, cáo đã nhanh chóng dựa vào những cái sừng dài của dê để leo lên khỏi giếng. Cáo quay lại nói: “Cậu thật ngốc. Nếu có hạn hán, thì những con chim đã thông báo với muông thú trong rừng rồi”.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Không bao giờ tin tưởng ai đó một cách mù quáng.\n','https://toplist.vn/images/800px/de-va-cao-230193.jpg',1)";

    private String SQLQuery13 = "INSERT INTO truyen VALUES (null,'Tại sao đít khỉ lại có màu đỏ','Ngày xưa, ở một ngôi làng kia, có rất nhiều chàng trai và cô gái. Mỗi tối sau khi dùng bữa xong, họ tụ tập quanh đống lửa và bắt đầu nhảy múa. Do đó, ngôi làng này có tên gọi là Ngôi làng nhảy múa.\n" +
            "\n" +
            "Một đêm nọ, có một con khỉ đến gần nơi đang diễn ra lửa trại. Nó mặc quần áo, đeo kính giống như con người và mang theo một số món quà nhỏ để tặng cho các cô gái. Mọi người đang hòa mình vào âm nhạc và nhảy múa nên chẳng ai nhận ra nó là một con khỉ cả.\n" +
            "\n" +
            "Cứ như vậy, mỗi đêm con khỉ lại đến và tặng quà cho các cô gái. Điều này làm các cô gái yêu quý khỉ hơn, còn các chàng trai ghen tỵ với khỉ. Họ quyết định phải tìm ra cho được người hay tặng quà cho các cô gái thật sự là ai. Thế là, như mọi hôm, con khỉ lại đến nhảy múa. Sau khi nhảy múa, các chàng trai theo dõi và phát hiện ra đó chỉ là một con khỉ. Các chàng trai quyết định dạy cho con khỉ một bài học.\n" +
            "\n" +
            "Hôm sau, họ đặt một bếp lửa nóng ở nơi con khỉ hay ngồi và dùng lá che lại. Khi con khỉ ngồi xuống, nó nhảy dựng lên đau đớn. Các cô gái ngơ ngác không hiểu chuyện gì đang xảy ra nên các chàng trai đã giải thích cho họ hiểu. Mọi người đều quyết định đuổi con khỉ đi. Kể từ đó, đít của con khỉ bắt đầu có màu đỏ do ảnh hưởng của vết bỏng hôm nào.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Đừng bao giờ đóng giả làm người khác.\n','https://toplist.vn/images/800px/tai-sao-dit-khi-lai-co-mau-do-230177.jpg',1)";





    // tạo bảng tại phương thức này
    public DatabaseSendTruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // truy vấn các câu lệnh k trả về kết quả
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
        db.execSQL(SQLQuery9);
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery10);
        db.execSQL(SQLQuery11);
        db.execSQL(SQLQuery12);
        db.execSQL(SQLQuery13);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // lấy 3 truyện mới nhất
    public Cursor getData1(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM " + TABLE_TRUYEN + " ORDER BY " +ID_TRUYEN +" DESC LIMIT 5",null);
        return res;
    }
    // lấy tất cả truyện
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM "+ TABLE_TRUYEN,null);
        return res ;
    }
}
