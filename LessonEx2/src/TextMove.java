import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextMove  implements ActionListener {
    private MyNotebook myNotebook;
    public TextMove(MyNotebook notebook) {
        myNotebook = notebook;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        myNotebook.getTextArea().append(myNotebook.getTextField().getText());
    }
}
