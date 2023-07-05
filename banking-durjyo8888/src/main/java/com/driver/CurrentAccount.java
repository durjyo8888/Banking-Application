package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception\
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if (balance < 5000) throw new Exception("Insufficient Balance");

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isNumberValid(tradeLicenseId)) {

            String rearrangedId = arrangeString(tradeLicenseId);
            if (rearrangedId.equals("")) {
                throw new Exception("Valid License can not be generated");
            } else
                this.tradeLicenseId = tradeLicenseId;
        }
    }

    private String arrangeString(String s) {
        int n = s.length();
        int freq[] = new int[26];
        for(char ch : s.toCharArray()) {
            freq[ch - 'A']++;
        }

        char ch_max = getMaxCountChar(freq);
        int maxCount = freq[ch_max - 'A'];
        if(n % 2 == 0) { // even case
            if (maxCount > ((n / 2) + 1))  return "";
        } else //odd case
            if (maxCount > ((n / 2) + 2))  return "";

        //possible way
        char[] ans = new char[n];

        //filling alternate places
        //fill the most frequent char
        int idx = 0;
        for(idx = 0; idx < n; idx += 2) {
            if(freq[ch_max] > 0) {
                ans[idx] = ch_max;
                freq[ch_max]--;
            } else break;
        }
        //fill remaining elements
        for(int i = 0; i < 26; i++) {
            char ch = (char) ('A' + i);

            while (freq[i] > 0) {
                if(idx >= n) idx = 1;
                ans[idx] = ch;
                idx += 2;
                freq[i]--;
            }
        }
        return new String(ans);
    }

    private char getMaxCountChar(int[] freq) {
        int max = 0;
        char ch = 0;
        for(int i = 0; i < 26; i++) {
            if(freq[i] > max){
                max = freq[i];
                ch = (char) ('A' + i);
            }
        }
        return ch;
    }

    private boolean isNumberValid(String id) {
        for(int i = 0; i < id.length() - 1; i++) {
            if(id.charAt(i) == id.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
