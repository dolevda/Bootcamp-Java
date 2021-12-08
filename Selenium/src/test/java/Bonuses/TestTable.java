package Bonuses;

import org.testng.annotations.Test;


public class TestTable extends ActionTable{

    @Test
    public void numCol(){
        System.out.println("The number of column in first page is:");
        System.out.println(tablePage.columnTable.size());
    }

    @Test
    public void numRow(){
        System.out.println("The number of row in first page is:");
        sumRows();
        System.out.println(sumRows);
    }
    @Test
    public void ageNameNy(){
        System.out.println("The names and ages in the first page that live in NY is:");
        printNameAge();
    }
    @Test
    public void allAgeNameNy(){
        System.out.println("The names and ages in all the table that live in NY is:");
        printAllAgeNameNy();
    }
    /*
* תרגיל בונוס לממש עם PO
בסעיף 4, להשתמש עם SoftAssert
ולוודא שכל האנשים שגרים בניו-יורק (בכל הדפים) מעל גיל 22
*
* */

    @Test
    public void bonusTeat(){
        CheckNyBig22();
        soft.assertAll();
    }

    @Test
    public void printAvr(){
        calcAvr();
        System.out.println(avrSalary);

    }



}
