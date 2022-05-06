package Main;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import java.io.IOException;

public class Convert {
    private static Convert convert;
    public static Convert getInstance(){
    convert = new Convert();
    return convert;
    }
    public boolean convertImages(String sourcePath, int width, int height, String savePath) throws IOException, InterruptedException, IM4JavaException {
        //Convert image to input size
        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage(sourcePath);
        op.resize(width, height);
        op.addImage(savePath);
        cmd.run(op);

        return true;
    }

}
