package com.uttesh.exude.stopping;

import com.uttesh.exude.common.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class StoppingParser {
    
    public static StoppingParser instance = null;

    public static StoppingParser getInstance() {
        if (instance == null) {
            instance = new StoppingParser();
        }
        return instance;
    }
    
    public void finalData(StringBuilder finalFilteredData,File outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Constants.UTF_8));
            writer.write(finalFilteredData + " ");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String filterStoppingWords(String data) {
        String[] words = data.split(Constants.SPACE);
        StringBuilder filteredWords = new StringBuilder();
        try {
            if (words.length > 0) {
                for (String word : words) {
                    filterWord(word.trim(), filteredWords);
                }
            } else {
                filterWord(data.trim(), filteredWords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredWords.toString();
    }

    private void filterWord(String word, StringBuilder filteredWords) {
        try {
            if (word.length() > 1 && word.length() < 6) {
                word = word.toLowerCase();
                char ch = word.charAt(0);
                int chascii = (int) ch;
                boolean notStopping = true;
                if (Character.isLetter((char) ch)) {
                    if (chascii != Constants.Letters.RejectLetters.K_ASCII
                            || chascii != Constants.Letters.RejectLetters.P_ASCII
                            || chascii != Constants.Letters.RejectLetters.Q_ASCCI
                            || chascii != Constants.Letters.RejectLetters.V_ASCII
                            || chascii != Constants.Letters.RejectLetters.X_ASCII
                            || chascii != Constants.Letters.RejectLetters.Z_ASCII) {
                        switch (chascii) {
                            case Constants.Letters.A_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_A_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.B_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_B_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.C_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_C_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.D_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_D_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.E_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_E_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.F_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_F_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.G_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_G_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.H_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_H_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.I_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_I_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.J_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_J_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.L_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_L_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.M_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_M_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.N_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_N_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.O_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_O_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.R_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_R_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.S_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_S_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.T_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_T_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.U_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_U_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.W_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_W_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                            case Constants.Letters.Y_ASCCI:
                                if (Constants.COMMON_EN_STOPPING_Y_WORDS.contains(word)) {
                                    notStopping = false;
                                }
                                break;
                        }

                    }
                }

                if (notStopping) {
                    filteredWords.append(word + " ");
                }
            } else {
                filteredWords.append(word + " ");
            }
        } catch (Exception e) {

        }
    }

}
