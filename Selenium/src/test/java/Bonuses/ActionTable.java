package Bonuses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ActionTable extends commonOps {

    @Step("Keep all row in per page")
    public void rowPrePage() {
        temp = driver.findElements(By.xpath("//*[@id='example']/tbody/tr"));
    }

    @Step("Click next and move for all pages")
    public boolean moveNextPage() {
        boolean flag = false;
        if (driver.findElement(By.id("example_next")).getAttribute("tabindex").equals("0")) {
            tablePage.btn_next.click();
            flag = true;
        }
        return flag;
    }

    @Step("Sum all the row in table")
    public void sumRows() {
        sumRows += tablePage.rowsFirstPage.size();
        rowPrePage();
        while (moveNextPage()) {
            rowPrePage();
            sumRows += temp.size();

        }
    }

    @Step("names of people live in New York per page")
    public void printNameAge() {
        for (int i = 0; i < tablePage.rowsFirstPage.size(); i++) {
            if (tablePage.offices.get(i).getText().equals("New York")) {
                System.out.println(tablePage.names.get(i).getText());
                System.out.println(tablePage.ages.get(i).getText());

            }
        }
    }

    @Step
    public void printAllAgeNameNy() {
        printNameAge();
        while (moveNextPage()) {
            printNameAge();
        }

    }

    @Step
    public void CheckNyBig22() {
        int ageTemp;
        for (int i = 0; i < tablePage.rowsFirstPage.size(); i++) {
            if (tablePage.offices.get(i).getText().equals("New York")) {
                ageTemp = Integer.parseInt(tablePage.ages.get(i).getText());
                soft.assertTrue(ageTemp > 22, ageTemp + "less then 22");
            }
        }
    }

    @Step
    public void calcAvr() {
        int count = 0;
        addSalary();
        while (moveNextPage()){
            rowPrePage();
            addSalary();
            count++;
        }
        avrSalary = avrSalary/count;


    }

    @Step
    public void addSalary() {
        float numTemp;
        for (int i = 0; i < tablePage.rowsFirstPage.size(); i++) {
            if (tablePage.positions.get(i).getText().equals("Software Engineer")) {
                numTemp=Float.parseFloat(tablePage.salary.get(i).getAttribute("textContent").replaceAll("[^-?0-9]+", ""));
                avrSalary+=numTemp;

            }
        }
    }
}