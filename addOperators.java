// Time complexity: Exponential : O(4^n)  where n is  the length of the nums array.
// O(N * 4^n) in the worst case scenario for backtracking using string builder
// Space complexity: O(n)   the size of recursion stack 
// o(H) under the hood


// Approach: Without Using Backtracking

class Solution {

    List<String> result;

    int targ;

    public List<String> addOperators(String num, int target) {

        result = new ArrayList<>();

        targ = target;

        if(num == null || num.length() == 0) return result;

        helper(num, "", 0, 0, 0);

        return result;

    }

    private void helper(String num, String path, long calc, long tail, int index){

        // base

        if(index == num.length()){

            if(targ == calc){

                result.add(path);

            }

            return;

        }

        // logic

        for(int i = index; i < num.length(); i++){

            // preceding zero

            if(num.charAt(index) == '0' && index != i) break;

            long curr = Long.parseLong(num.substring(index, i+ 1)); 

            if(index == 0){

                helper(num, path + curr, curr, curr, i+ 1);

            } else {

                // +

                helper(num, path + "+" + curr, calc + curr, curr, i+ 1);

                // -

                helper(num, path + "-" + curr, calc - curr, -curr, i+ 1);

                // *

                helper(num, path + "*" + curr, calc - tail + tail * curr, tail*curr, i+1);

            }

        }

    }

}



///With Backtracking



class Solution {

    List<String> result;

    public List<String> addOperators(String num, int target) {

        result = new ArrayList<>();

        helper(num, target, new StringBuilder(), 0, 0, 0);

        return result;

    }

    private void helper(String num, int target, StringBuilder sb, long calc, long tail, int index){

        // base 

        if(index == num.length()){

            if(calc == target){

                result.add(sb.toString());

                return;

            }

        }

        // logic

       for(int i = index; i < num.length(); i++){

           // preceding zero case

           // if(index != i && num.charAt(index) == '0') break;

           long curr = Long.parseLong(num.substring(index, i + 1)); // 1*5

           int len = sb.toString().length();

           if(index == 0){

               sb.append(curr); //action

               helper(num, target, sb ,curr, curr, i + 1); // recurse

               sb.setLength(len); // backtrack

           } else {

               sb.append("+");//action

               sb.append(curr);//action

               helper(num, target, sb, calc + curr, curr, i + 1); // recurse

               sb.setLength(len);// backtrack

               

               sb.append("-");//action

               sb.append(curr);//action

               helper(num, target, sb, calc - curr, -curr, i + 1); // // recurse

               sb.setLength(len);// backtrack

               

               sb.append("*");

               sb.append(curr);

               helper(num, target, sb, calc - tail + tail*curr, tail*curr, i + 1); // recurse

               sb.setLength(len);// backtrack

           }

       }

    }

}