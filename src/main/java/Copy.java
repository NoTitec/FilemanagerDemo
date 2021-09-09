import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Copy
{
    Copy(){

    }
    void start(String s,String d){
        try {
            copy(s,d);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void copy(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);

// 소스 파일 검사
        if (!fromFile.exists())  throw new IOException("파일 존재하지 않음: " + from);
        if (!fromFile.isFile())  throw new IOException("디렉토리 복사 불가: " + from);
        if (!fromFile.canRead()) throw new IOException("소스파일 읽기 불가: " + from);

// destination이 디렉토리인 경우 목적 파일 이름 생성: source file과 같은 이름
        if (toFile.isDirectory())
            toFile = new File(toFile, fromFile.getName()); // new File(parent, child)

        // 목적 파일 검사
        if (toFile.exists()) {
            if (!toFile.canWrite()) abort("cannot write the destination file: " + to);
            System.out.println("덮어쓰기 할까요? " + to + " (Y/N): ");
            Scanner s = new Scanner(System.in);
            String response = s.nextLine();
            if (!response.equals("Y") && !response.equals("y")) abort("덮어쓰기 하지 않음.");
        } else { 
            String parent = toFile.getParent(); // 목적 디렉토리 경로명
            if (parent == null) // 목적 디렉토리가 주어지지 않으면, 현재 디렉토리를 목적 디렉토리로 설정함
                parent = System.getProperty("user.dir"); // current (or working) directory 획득함
            File toDir = new File(parent);
            if (!toDir.exists()) 	abort("목적 디렉토리 존재하지 않음: " + parent);
            if (toDir.isFile()) 	abort("목적지가 디렉토리 아님: " + parent);
            if (!toDir.canWrite()) 	abort("목적 디렉토리 쓰기 불가: " + parent);
        }

        // 파일 복사
        try (FileInputStream in = new FileInputStream(fromFile);
             FileOutputStream out = new FileOutputStream(toFile)) // try-with-resource clause
        {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) // -1: end of stream indicator
                out.write(buffer, 0, bytesRead);
        }
    } // end of copy( )

    private static void abort(String msg) throws IOException {
        throw new IOException("FileCopy: " + msg);
    }

}
