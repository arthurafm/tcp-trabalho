package visuals;

import audio.Audio;
import files.ReadFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends javax.swing.JFrame {

    public Gui() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
                        
    private void initComponents() {

        text = new java.awt.TextArea();
        generateMusicButton = new javax.swing.JButton();
        saveMusicButton = new javax.swing.JButton();
        openMusicButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        generateMusicButton.setText("Play");
        generateMusicButton.setActionCommand("generateMusic");
        generateMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = text.getText();
                Audio audio = new Audio(prompt);
                audio.playMusic();
            }
        });

        saveMusicButton.setText("Save MIDI File");
        saveMusicButton.setActionCommand("saveMusic");
        saveMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio audio = new Audio(text.getText());
                audio.saveMidiFile();
            }
        });

        openMusicButton.setText("Read Text File");
        openMusicButton.setActionCommand("openMusic");
        openMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile readFile = new ReadFile();
                String textToMusic = readFile.read();
                text.setText(text.getText() + textToMusic);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(saveMusicButton)
                    .addComponent(openMusicButton))
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generateMusicButton)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(openMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap(221, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }

    public static void generateMusic(){

    }

    private static javax.swing.JButton generateMusicButton;
    private static javax.swing.JButton saveMusicButton;
    private static javax.swing.JButton openMusicButton;
    private static java.awt.TextArea text;
}