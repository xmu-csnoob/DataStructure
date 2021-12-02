package exception;

/**
 * @author Wenfei Wang
 * 数据操作时影响数据安全的操作抛出此异常
 */
public class DataSafetyException extends Exception{
    public DataSafetyException(String msg){
        super(msg);
    }
}
