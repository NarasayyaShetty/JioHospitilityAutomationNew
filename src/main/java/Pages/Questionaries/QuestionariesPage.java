package Pages.Questionaries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuestionariesPage {
    WebDriver driver;

    public QuestionariesPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5),this);
    }

    @FindBy(xpath="//button[text()='Add More']")
    private WebElement addMoreButton;

    @FindBy(css="input.new")
    private WebElement questionField;

    @FindBy(id ="save_questions")
    private WebElement saveButton;

    @FindBy(id ="noticeContent")
    private WebElement successToastMessage;

    @FindBy(css ="button.editquestModal")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//h1[text()='Edit Question']")
    private WebElement editQuestionPopUp;

    @FindBy(id = "question")
    private WebElement updateQuestionTextField;

    @FindBy(id ="update_questions")
    private WebElement updateQuestionButton;

    @FindBy(id="noticeContent")
    private WebElement updateSuccessfulToastMessage;

    public boolean addQuestions(String question){
        boolean flag=false;
        try{
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            Actions action=new Actions(driver);

            wait.until(ExpectedConditions.visibilityOf(addMoreButton));
            action.moveToElement(addMoreButton).click(addMoreButton).perform();

            wait.until(ExpectedConditions.visibilityOf(questionField));
            questionField.click();
            questionField.sendKeys(question);

            action.moveToElement(saveButton).click(saveButton).perform();

            wait.until(ExpectedConditions.visibilityOf(successToastMessage));
            flag=successToastMessage.getText().contains("successfully created");

            wait.until(ExpectedConditions.invisibilityOf(successToastMessage));

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during adding questions");
        }
        return flag;
    }

    public boolean editQuestionaries(String oldQuestion, String newQuestion){
        boolean flag=false;
        try{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            for(WebElement editButton:editButtons){
                String value=editButton.getAttribute("data-question");
                if(value.equalsIgnoreCase(oldQuestion)){
                    System.out.println(value);
                    editButton.click();
                    break;
                }
            }
            wait.until(ExpectedConditions.visibilityOf(editQuestionPopUp));
            updateQuestionTextField.clear();
            updateQuestionTextField.sendKeys(newQuestion);
            updateQuestionButton.click();
            flag=wait.until(ExpectedConditions.visibilityOf(updateSuccessfulToastMessage)).getText().contains("Question updated successfully");

            wait.until(ExpectedConditions.invisibilityOf(updateSuccessfulToastMessage));


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is occurred during the edit the questionaries");

        }
        return flag;
    }

}
