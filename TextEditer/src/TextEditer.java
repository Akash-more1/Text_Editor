import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditer implements ActionListener {
    //declaring properties of textEditer
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    //file menu items
    JMenuItem  newFile,openFile, saveFile;

    //edit menu items
    JMenuItem cut,copy,past, selectAll, close;

    JTextArea textArea;
    TextEditer(){
        //initialising a frame
        frame=new JFrame("Text Editer");

        //initialise textArea
        textArea=new JTextArea();

        //initialise menuBar
        menuBar=new JMenuBar();

        //initialise menus
        file=new JMenu("file");
        edit=new JMenu("edit");

        //initialise file menu items
        newFile= new JMenuItem("New window");
        openFile=new JMenuItem("openFile");
        saveFile=new JMenuItem("saveFile");

        //adding actionlister to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //adding file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialise edit menu items
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        past=new JMenuItem("past");
        selectAll=new JMenuItem("selectAll");
        close=new JMenuItem("close");

        //adding actionlisteners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        past.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(past);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //set menuBar to frame
        frame.setJMenuBar(menuBar);

        //create content panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);

        //creating scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //adding scroll pane to panel
        panel.add(scrollPane);


        //add textArea to frame
        frame.add(panel);

        //setting Dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop programe when hit X button

    }

    //overriding method ActionListner interface
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }

        if(actionEvent.getSource()==past){
            //perform past operation
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            //open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);

            //if we clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get selected file
                File file=fileChooser.getSelectedFile();
                //get a path of selected file
                String filepath=file.getPath();

                try{
                    //initialise file reader
                    FileReader fileReader=new FileReader(filepath);
                    //initialize bufferd Reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);

                    String intermidiate=" ", output=" ";

                    //reading conteant of file line by line
                    while((intermidiate=bufferedReader.readLine())!=null){
                        output+=intermidiate+"\n";
                    }

                    //set output string to textarea
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException) {
                   ioException.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource()==saveFile){

            //initialize file chooser
            JFileChooser fileChooser=new JFileChooser("C:\\Users\\ASUS\\OneDrive\\Desktop");
            //get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);

            //check of we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                  File file =new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                  //initialize file writer
                  try{
                      FileWriter fileWriter=new FileWriter(file);

                      //initialize bufferd writer
                      BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);

                      //write content of text area to file
                      textArea.write(bufferedWriter);
                      bufferedWriter.close();
                  }
                  catch (IOException ioException){
                        ioException.printStackTrace();
                  }
            }
        }

        if(actionEvent.getSource()==newFile){
            //innitialize new object of TextEditer class
            TextEditer newTextEditor=new TextEditer();
        }

    }
    public static void main(String[] args) {

        TextEditer textEditer=new TextEditer();
    }


}