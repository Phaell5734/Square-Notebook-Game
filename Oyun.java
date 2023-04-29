package proje33;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JFrame;


public class Oyun extends JFrame implements ActionListener {
    private int boyut;
    private JPanel oyunPaneli;
    private JButton[][] kareler;
    private boolean[][] seciliKareler;
    private int xIndex = 0,yIndex = 0;
    int i,j;
    
    public Oyun(int boyut) {
        super("Oyun");
        this.boyut = boyut;
        this.oyunPaneli = new JPanel(new GridLayout(boyut, boyut));
        this.oyunPaneli.setSize(10000, 10000);
        this.kareler = new JButton[boyut][boyut];
        this.seciliKareler = new boolean[boyut][boyut];
        for (int i = 0; i < boyut; i++) {
            for (int j = 0; j < boyut; j++) {
                kareler[i][j] = new JButton();
                kareler[i][j].addActionListener(this);
                oyunPaneli.add(kareler[i][j]);
            }
        }
        add(oyunPaneli);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton kare = (JButton)e.getSource();
        int satir = 0,sutun = 0;

        for (int i = 0; i < boyut; i++) {
            for (int j = 0; j < boyut; j++) {
                if (kareler[i][j] == kare) {
                    satir = i;
                    sutun = j;  
                }
            }
        }
                 if (!seciliKareler[satir][sutun]) {
                	 if(this.xIndex == 0 && this.yIndex == 0) {
                		 this.xIndex = satir;
                		 this.yIndex = sutun;
                		 seciliKareler[satir][sutun] = true;
                         kare.setBackground(Color.BLACK);
                	 }
                	 else if ((Math.abs(satir-this.xIndex) + Math.abs(sutun-this.yIndex)) % 3 == 0 && (Math.abs(satir-this.xIndex) + Math.abs(sutun-this.yIndex))/3 == 1 ) {
                		 if(Math.abs(satir-this.xIndex) % 3 != 0 && Math.abs(sutun-this.yIndex) % 3 != 0) {
                		 this.xIndex = satir;
                		 this.yIndex = sutun;
                         seciliKareler[satir][sutun] = true;
                         kare.setBackground(Color.BLACK);
                		 }
                     }
        }
        oyunBittiMi();
    }

    private void oyunBittiMi() {
        int gidilebilenKareSayisi = 0;
        for (int i = 0; i < kareler.length; i++) {
			for (int j = 0; j < kareler.length; j++) {
				if((Math.abs(this.xIndex-i) + Math.abs(this.yIndex-j)) % 3 == 0 && !seciliKareler[i][j] && (Math.abs(this.xIndex-i) + Math.abs(this.yIndex-j)) / 3 == 1) {
					if(Math.abs(this.xIndex-i) % 3 != 0 && Math.abs(this.yIndex-j) % 3 != 0) {
					gidilebilenKareSayisi++;
                                        System.out.println("X="+this.xIndex+" "+i+" Y="+this.yIndex+" "+j+" Gidilebilen kare="+gidilebilenKareSayisi);
					}
				}
			}
		}
        
        if (gidilebilenKareSayisi == 0) {
        	  JOptionPane.showMessageDialog(null, "Maalesef! Oyunu kaybettiniz!");
              tekrarOyna();
        }
        
        for (i = 0; i < boyut; i++) {
            for (j = 0; j < boyut; j++) {
                if (!seciliKareler[i][j]) {
                    return;
                } 
              }
            }
        JOptionPane.showMessageDialog(null, "Tebrikler! Oyunu kazandınız!");
        tekrarOyna();
    }
 
	private void tekrarOyna() {
		int tekrarOyna = JOptionPane.showConfirmDialog(null, "Tekrar oynamak istiyor musunuz?");
        if (tekrarOyna == JOptionPane.YES_OPTION) {
            for (i = 0; i < boyut; i++) {
                for (j = 0; j < boyut; j++) {
                    kareler[i][j].setBackground(null);
                    seciliKareler[i][j] = false;
                }
            }
        }
        else {
            dispose();
        }
	}

	public static void main(String[] args) {
	    String[] boyutSecenekleri = {"5x5", "6x6", "7x7", "8x8", "9x9"};
	    int boyut = Integer.parseInt(((String) JOptionPane.showInputDialog(null, "Oyunun boyutunu seçin:",
	            "Boyut Seçimi", JOptionPane.QUESTION_MESSAGE, null,
	            boyutSecenekleri, boyutSecenekleri[0])).substring(0, 1));
	    new Oyun(boyut);
	}

}