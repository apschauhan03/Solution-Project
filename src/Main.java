import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0;i<expression.length();i++){
            char ch = expression.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch-'0'>1&&ch-'0'<=9){
                operands.push(ch-'0');
            }
            if(ch-'0'==1){
                if(i!=expression.length()-1) {
                    if (expression.charAt(i + 1) - '0' == 0) {
                        operands.push(10);
                    } else {
                        operands.push(1);
                    }
                }
                else {
                    operands.push(1);
                }
            }
            if(ch=='+'||ch=='-'||ch=='*'||ch=='/'){
                 while(operators.size()>0&&(Precedence(ch)<=Precedence(operators.peek()))){
                     char o1 =operators.pop();
                     int v2 = operands.pop();
                     int v1 = operands.pop();

                     int opv = Evaluate(o1,v1,v2);
                     operands.push(opv);


                }
                 operators.push(ch);
            }

        }
        while (operators.size()!=0){
            char o1 =operators.pop();
            int v2 = operands.pop();
            int v1 = operands.pop();

            int opv = Evaluate(o1,v1,v2);
            operands.push(opv);

        }

        System.out.println(operands.peek());

    }

    public static int Precedence(char operator){
        int ans = 0;
        if(operator=='+'||operator=='-'){
            ans = 1;
        }
        if(operator=='*'||operator=='/'){
            ans = 2;
        }
        return ans;
    }

    public static int Evaluate(char x,int a,int b){
        int ans = 0;
        switch (x) {
            case '+':
                ans = a+b;
                break;

            case '-':
                ans = a-b;
                break;

            case '*':
                ans = a*b;
                break;

            case '/':
                ans = a/b;

        }
        return ans;
    }

}