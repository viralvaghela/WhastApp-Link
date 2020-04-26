package application;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.net.URI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
	String link="";
	 Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    @FXML
    private JFXTextField txtNo;

    @FXML
    private JFXTextArea txtText;

    @FXML
    private JFXButton btnGenerate;
    
    @FXML
    private Hyperlink hyperlink;
    
    @FXML
    private ImageView imgqrcode;
    
    @FXML
    private JFXButton btnShareFb;

    @FXML
    private JFXButton btnShareTwitter;
    
    @FXML
    void generateLink(ActionEvent event)
    {
    
    	link="https://wa.me/"+txtNo.getText()+"?text="+txtText.getText();
    	System.out.println(link);
    	hyperlink.setText(link);
    	hyperlink.setVisible(true);
    	BufferedImage bimage =  generateQR(link);
    	Image image = SwingFXUtils.toFXImage(bimage,null);
    	imgqrcode.setImage(image);
    }
    
    @FXML
    void shareOnFacebook(ActionEvent event) {
    	
    	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
    	        try {
    	        		
    	        	String fblink="https://www.facebook.com/sharer.php?u=";
    	        	fblink=fblink+link;
     	            desktop.browse(new URI(fblink));
    	           
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    }

    @FXML
    void shareOnTwitter(ActionEvent event) {
    	 if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
 	        try {
 	        	String twlink="https://twitter.com/intent/tweet?text=Whatsapp%20Me&url=";
 	        	twlink=twlink+link;
 	        	desktop.browse(new URI(twlink));
 	           
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	        }
 	    }
    }
    
    @FXML
    void shareOnLinkedIn(ActionEvent event) {
    	 if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
 	        try {
 	        	String lnkdlink="https://www.linkedin.com/sharing/share-offsite/?url=";
 	        	lnkdlink=lnkdlink+link;
 	        	desktop.browse(new URI(lnkdlink));
 	           
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	        }
 	    }
    }
    

	private BufferedImage generateQR(String link) {
		 QRCodeWriter barcodeWriter = new QRCodeWriter();
		    BitMatrix bitMatrix=null;
			try {
				bitMatrix = barcodeWriter.encode(link, BarcodeFormat.QR_CODE, 200, 200);
				System.out.println("QR generated");
			} 
			catch (WriterException e) {
				
				e.printStackTrace();
			}
			return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
