class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for(int num : data) {
            num = num & 0xFF; // take last 8 bits

            if(remaining == 0) {
                if((num >> 7) == 0) {
                    continue; // 1-byte char
                }

                int count = 0;
                for(int i = 7; i >= 0; i--) {
                    if(((num >> i) & 1) == 1)
                        count++;
                    else
                        break;
                }

                if(count == 1 || count > 4)
                    return false;

                remaining = count - 1;
            }
            else {
                if((num >> 6) != 0b10)
                    return false;

                remaining--;
            }
        }

        return remaining == 0;
    }
}