import java.io.File;

public class Delete {
    String path;
    Delete(){
        path="";
    }
    void start(String path){
        try {
            delete(path);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }
    public static void delete(String filename) {
        File f = new File(filename);

// 목적 파일 검사
        if (!f.exists()) fail("Delete: no such file or directory: " +filename);
        if (!f.canWrite()) fail("Delete: write protected: " + filename);

// empty directory 검사
        if (f.isDirectory()) {
            String[] fileList = f.list();
            if (fileList.length > 0)
                fail("Delete: directory not empty: " + filename);
        }

// 파일 삭제
        if (!f.delete())
            fail("Delete: deletion failed");
    }
    protected static void fail(String msg) throws IllegalArgumentException {
        throw new IllegalArgumentException(msg);
    }

}
