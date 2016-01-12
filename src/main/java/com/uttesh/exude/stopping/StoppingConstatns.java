package com.uttesh.exude.stopping;

/**
 *
 * @author Rivet Systems, Inc.
 */
public interface StoppingConstatns {

    /**
     * a : ASCII: 97 b : ASCII: 98 c : ASCII: 99 d : ASCII: 100 e : ASCII: 101 f
     * : ASCII: 102 g : ASCII: 103 h : ASCII: 104 i : ASCII: 105 j : ASCII: 106
     * k : ASCII: 107 -- l : ASCII: 108 m : ASCII: 109 n : ASCII: 110 o : ASCII:
     * 111 p : ASCII: 112 -- q : ASCII: 113 -- r : ASCII: 114 s : ASCII: 115 t :
     * ASCII: 116 u : ASCII: 117 v : ASCII: 118 -- w : ASCII: 119 x : ASCII: 120
     * -- y : ASCII: 121 z : ASCII: 122 --
     *
     */
    String STOPPING_EN_FILE = "com/uttesh/data/stopping_en.properties";

    String COMMON_EN_STOPPING_A_WORDS = "a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at";
    String COMMON_EN_STOPPING_B_WORDS = "be,because,been,but,by,being";
    String COMMON_EN_STOPPING_C_WORDS = "can,cannot,could";
    String COMMON_EN_STOPPING_D_WORDS = "dear,did,do,does";
    String COMMON_EN_STOPPING_E_WORDS = "either,else,ever,every";
    String COMMON_EN_STOPPING_F_WORDS = "for,from";
    String COMMON_EN_STOPPING_G_WORDS = "get,got";
    String COMMON_EN_STOPPING_H_WORDS = "had,has,have,he,her,hers,him,his,how,however";
    String COMMON_EN_STOPPING_I_WORDS = "i,if,in,into,is,it,its";
    String COMMON_EN_STOPPING_J_WORDS = "just";
    String COMMON_EN_STOPPING_L_WORDS = "least,let,like,likely";
    String COMMON_EN_STOPPING_M_WORDS = "may,me,might,most,must,my";
    String COMMON_EN_STOPPING_N_WORDS = "neither,no,nor,not";
    String COMMON_EN_STOPPING_O_WORDS = "of,off,often,on,only,or,other,our,own";
    String COMMON_EN_STOPPING_R_WORDS = "rather";
    String COMMON_EN_STOPPING_S_WORDS = "said,say,says,she,should,since,so,some,such";
    String COMMON_EN_STOPPING_T_WORDS = "than,that,the,their,them,then,there,these,they,this,tis,to,too,twas";
    String COMMON_EN_STOPPING_U_WORDS = "us";
    String COMMON_EN_STOPPING_W_WORDS = "wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,without";
    String COMMON_EN_STOPPING_Y_WORDS = "yet,you,your";
    String COMMON_EN_STOPPING_WORDS_WITH_CONTRACTION = "'tis,'twas,ain't,aren't,can't,could've,couldn't,didn't,doesn't,don't,hasn't,he'd,he'll,he's,hers,him,his,how,how'd,how'll,how's,however,i,i'd,i'll,i'm,i've,if,in,into,is,isn't,it,it's,its,just,least,let,like,likely,may,me,might,might've,mightn't,most,must,must've,mustn't,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,shan't,she,she'd,she'll,she's,should,should've,shouldn't,since,so,some,than,that,that'll,that's,the,their,them,then,there,there's,these,they,they'd,they'll,they're,they've,this,tis,to,too,twas,us,wants,was,wasn't,we,we'd,we'll,we're,were,weren't,what,what'd,what's,when,when,when'd,when'll,when's,where,where'd,where'll,where's,which,while,who,who'd,who'll,who's,whom,why,why'd,why'll,why's,will,with,won't,would,would've,wouldn't,yet,you,you'd,you'll,you're,you've,your";

    interface Letters {

        int K_ASCII = 107;
        int P_ASCII = 112;
        int Q_ASCCI = 113;
        int V_ASCII = 118;
        int X_ASCII = 120;
        int Z_ASCII = 122;

        int A_ASCCI = 97;
        int B_ASCCI = 98;
        int C_ASCCI = 99;
        int D_ASCCI = 100;
        int E_ASCCI = 101;
        int F_ASCCI = 102;
        int G_ASCCI = 103;
        int H_ASCCI = 104;
        int I_ASCCI = 105;
        int J_ASCCI = 106;
        int L_ASCCI = 108;
        int M_ASCCI = 109;
        int N_ASCCI = 110;
        int O_ASCCI = 111;
        int R_ASCCI = 114;
        int S_ASCCI = 115;
        int T_ASCCI = 116;
        int U_ASCCI = 117;
        int W_ASCCI = 119;
        int Y_ASCCI = 121;
    }
}
