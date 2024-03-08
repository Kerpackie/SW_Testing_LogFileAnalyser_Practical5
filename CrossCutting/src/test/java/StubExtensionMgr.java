import CrossCutting.IExtensionMgr;

public class StubExtensionMgr implements IExtensionMgr
{
    private String logFileStatus = null;

    public void setLogFileStatus(String status)
    {
        logFileStatus = status;
    }

    @Override
    public String ReadLogFileToGetStatus(String fileName) {
        return logFileStatus;
    }
}
