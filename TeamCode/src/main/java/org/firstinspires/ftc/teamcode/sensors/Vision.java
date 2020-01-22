package org.firstinspires.ftc.teamcode.sensors;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robot;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * The Vision class has methods to handle Skystone detection.
 */
public class Vision {
    private VuforiaLocalizer mVuforia;

    /**
     * Initializes Vuforia.
     */
    public Vision() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVhfqRH/////AAABGd7wUb568kaDho9qW6uyIZV9ovIZF9UnMCqNzmBE1YeaiqGsmXkyZr3aGikHN++7DfnOeymbsUDQELp8AGTQRbYXf6re9h7csCPKXnY/YjlbOHCp7hzDRIJ3rXe+m1RmOIDjLUs8w6sauRzlhGH6qlWfqvBrp94N2NUMygqt4MMDlrXH5B2ieMgcaUJiA3yS9U27wLKcXzPLzhNa2Pj6uyDXAMIYC2ymfRVVOOecwr9wImJ5fiHjzXvJTwPoQ9hYgEn92jPl2Z+yEq225/hdTGSgTKhlFRQI5sM4otsL/vCH6avqjuwyTBC5189St7ZrMzNjsBIRTsTLOqlwFdasebS4d9hHGxfioYlq4+4fSvSF";;
        parameters.cameraDirection = CameraDirection.BACK;

        mVuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Gets the position of the Skystone.
     * @param alliance the current alliance
     * @return the position of the Skystone
     */
    public int getSkystonePosition(String alliance) {
        Bitmap bitmap = getBitmap();
        if(bitmap == null) {
            return Constants.kSkystoneOuter;
        }

        int start = alliance.equals("Red") ? Constants.kRedScanLineStart : Constants.kBlueScanLineStart;
        int end = alliance.equals("Red") ? Constants.kRedScanLineEnd : Constants.kBlueScanLineEnd;

        int yellow1 = 0;
        int yellow2 = 0;
        for(int i = start; i < end; i++) {
            int pixel = bitmap.getPixel(i, Constants.kScanLineY);

            if(i < (end + start) / 2) {
                // flip the positions since the Vuforia image is upside down for whatever reason
                yellow2 += Color.red(pixel) + Color.green(pixel);
            } else {
                yellow1 += Color.red(pixel) + Color.green(pixel);
            }
        }

        Robot.telemetry.addData("Yellow1", yellow1);
        Robot.telemetry.addData("Yellow2", yellow2);
        Robot.telemetry.addData("Yellow thresh", Constants.kYellowThresh);
        Robot.telemetry.update();

        if(yellow1 < Constants.kYellowThresh) {
            return alliance.equals("Red") ? Constants.kSkystoneCenter : Constants.kSkystoneOuter;
        } else if(yellow2 < Constants.kYellowThresh) {
            return alliance.equals("Red") ? Constants.kSkystoneOuter : Constants.kSkystoneCenter;
        } else {
            return Constants.kSkystoneInner;
        }
    }

    private Bitmap getBitmap() {
        Image img = getImage();
        if(img == null) {
            Robot.telemetry.addData("Vision", "No image!");
            Robot.telemetry.update();
            return null;
        }

        // copy the bitmap from the Vuforia frame
        Bitmap bitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
        bitmap.copyPixelsFromBuffer(img.getPixels());

        return bitmap;
    }

    /**
     * Returns the current image on the Vuforia stream
     * @return the current image on the Vuforia stream
     */
    private Image getImage() {
        Image image = null;
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        int originalCapacity = mVuforia.getFrameQueueCapacity();
        mVuforia.setFrameQueueCapacity(1);

        try {
            VuforiaLocalizer.CloseableFrame frame = mVuforia.getFrameQueue().take();

            for (int i = 0; i < frame.getNumImages(); i++) {
                if (frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                    image = frame.getImage(i);
                    break;
                }
            }
            frame.close();
        } catch (InterruptedException exc) {

        }

        mVuforia.setFrameQueueCapacity(originalCapacity);
        return image;
    }

    /**
     * Saves bitmap to a file as a .png
     */
    public void write() {
        Bitmap bitmap = getBitmap();
        if(bitmap == null) {
            return;
        }

        String name = Calendar.getInstance().getTime() + ".png";
        String path = Environment.getExternalStorageDirectory().getPath();
        try(FileOutputStream out = new FileOutputStream(new File(path, name))) {

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Robot.telemetry.addData("Wrote to", path + name);
            Robot.telemetry.update();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}