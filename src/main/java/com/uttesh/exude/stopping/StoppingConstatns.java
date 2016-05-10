/*
 Copyright 2016-2022 Uttesh Kumar T.H.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.uttesh.exude.stopping;

/**
 *
 * @author Uttesh Kumar T.H.
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
