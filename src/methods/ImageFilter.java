package methods;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return true;		
		}
		
        String extension = getFileExtension(f);
        if (extension != null) {
            extension = extension.toLowerCase();
            return extension.equals("jpg") || extension.equals("jpeg") ||
                    extension.equals("png") || extension.equals("gif");
        }

        return false;
    }
		
	

	@Override
	public String getDescription() {
		return "Filters for image files";
	}
	
	
	
	private String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < name.length() - 1) {
        	return name.substring(lastDotIndex + 1);
        }
        return null;
    }
	 
	

}
