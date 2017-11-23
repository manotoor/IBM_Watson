/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredient_gui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImage;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

/**
 *
 * @author Evan
 */
public class gui extends javax.swing.JFrame {
	private myImage[] imgNames = new ImageList().getImageNames();
	private VisualRecognition service = new VisualRecognition(
			  VisualRecognition.VERSION_DATE_2016_05_20
			);
	final int norm = NORMAL;
	final int imageHeight = 800;
	final int imageWidth = 525;
	final Container container = this;

	//Thread watson = null;
    /**
     * Creates new form gui
     */
    public gui() {
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }
  
    public void start() {
    	
    	/* OLD KEY */
//    	service.setApiKey("af16cab33a7b47433d5ce63aace1d08f379afa2a");
    	
    	service.setApiKey("f9ef8b5896c3db5e0a4a6caa297e2a011a825bab");
    	
    	System.out.println("imageHeight: " + imageHeight);
        ImageIcon icon = null;
        icon = imgNames[0].scale(imageHeight, imageWidth, norm);
		if(icon != null) {
			img.setIcon(icon);
		}else {
			System.out.println("was null");
		}
		
		imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageList.setCellRenderer(new ImgRenderer());
        imageList.setVisibleRowCount(1);
    	imageList.setListData(imgNames);
    	imageSelect.setViewportView(imageList);
    	imageSelect.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    	watsonInfo.setDragEnabled(true);
    	Dimension d = new Dimension(800,525);
    	img.setSize(d);
    	img.setMinimumSize(d);
    	img.setMaximumSize(d);
    }
    public static boolean isInternetReachable()
    {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
        	javax.swing.JOptionPane.showMessageDialog(null,
   				 "No Internet Connect Detected!", 
   				 "Alert", 
   				 javax.swing.JOptionPane.ERROR);
            return false;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
        	javax.swing.JOptionPane.showMessageDialog(null,
   				 "No Internet Connect Detected!", 
   				 "Alert", 
   				 javax.swing.JOptionPane.ERROR);
            return false;
        }
        return true;
    }
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ImgRenderer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
                return null;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectedImage = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        imageSelect = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        setImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        watsonInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/donut platter.jpg"))); // NOI18N
        img.setText("jLabel1");

        javax.swing.GroupLayout selectedImageLayout = new javax.swing.GroupLayout(selectedImage);
        selectedImage.setLayout(selectedImageLayout);
        selectedImageLayout.setHorizontalGroup(
            selectedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        selectedImageLayout.setVerticalGroup(
            selectedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageSelect.setViewportView(imageList);

        setImage.setText("Choose");
        setImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImageActionPerformed(evt);
            }
        });

        watsonInfo.setColumns(20);
        watsonInfo.setRows(5);
        watsonInfo.setMaximumSize(new java.awt.Dimension(172, 388));
        jScrollPane1.setViewportView(watsonInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectedImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setImage, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectedImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setImage, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(imageSelect))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setImageActionPerformed
        //String returnedInfo = "PUT WATSON INFO HERE";
    	final int i = imageList.getSelectedIndex();
    	final JButton b = (JButton) evt.getSource();
    	
        Runnable watsonThread = new Runnable() {
        	File f = null;
        	int x = 0;
			public void run() {
				if(i < 0) {
		    		f = new File(imgNames[0].getPath());
		    	}
		    	else {
		    		f = new File(imgNames[i].getPath());
		    	}
			    try{
			    	InputStream imagesStream = new FileInputStream(f);
			    	//can add parameters here too
			    	if (i < 0)
			    		x = 0;
			    	else
			    		x = i;
			    	if(isInternetReachable()) {
				    	ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				    	  .imagesFile(imagesStream)
				    	  .imagesFilename(imgNames[x].getImageName())
				    	  .build();
				    	ClassifiedImages result = service.classify(classifyOptions).execute();
				    	
				    	/* KEYWORD DISPLAY */
				    	
				    	// What image is being processed
				    	List<ClassifiedImage> resultList = result.getImages();
				    	System.out.println(resultList);
				    	System.out.println("---");
//				    	String[] badWords = {"dish", "nutrition", "food"};

				    	System.out.println(resultList.get(0).getClassifiers().get(0).getClasses().size());
				    	
				    	// List of objects of keywords/scores
				    	List<ClassResult> classResult = resultList.get(0).getClassifiers().get(0).getClasses();
				    	for (int z = 0; z < resultList.get(0).getClassifiers().get(0).getClasses().size(); z++) {
//				    		if (classResult.get(z).getClassName().contains(badWords[0]) || classResult.get(z).getClassName().contains(badWords[0])
//				    					|| classResult.get(z).getClassName().contains(badWords[0])) {
//				    			classResult.remove(classResult.get(z));
//				    		}
				    		System.out.println(classResult.get(z).getClassName());
				    		System.out.println(classResult.get(z).getScore());
				    	}
				    	
				    	
				    	// test to open the receipe website 
				    	// Need to replace "" with %20 since url does not take ""
				    	String searchTerm = classResult.get(0).getClassName().replaceAll(" ","%20");
			    		String url_open ="http://allrecipes.com/search/results/?wt=" + searchTerm + "&sort=re";
			    		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
				    	/* ****************** */
		
						watsonInfo.setText(result.toString());
						container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						b.setEnabled(true);
			    	}else {
			    		container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    		b.setEnabled(true);
			    	}
					Thread.sleep(1000);
			    }catch(Exception e){
			    }   
			}
		};
		Thread watson = new Thread(watsonThread);
		watson.start();
		if(i < 0) {
    		img.setIcon(imgNames[0].scale(imageHeight, imageWidth, norm));
    		watsonInfo.setText("Loading...\nPlease Wait!");
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    	}
    	else {
    		img.setIcon(imgNames[i].scale(imageHeight, imageWidth, norm));
    		watsonInfo.setText("Loading...\nPlease Wait!");
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    	}
        
    }//GEN-LAST:event_setImageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }
    
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList imageList;
    private javax.swing.JScrollPane imageSelect;
    private javax.swing.JLabel img;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel selectedImage;
    private javax.swing.JButton setImage;
    private javax.swing.JTextArea watsonInfo;
    // End of variables declaration//GEN-END:variables
}
