import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.SecretVersionName;

public class MyDoFn extends DoFn<KV<String,String>, Void> {
  private String pexelsKey;

  @Setup
  public void setup() throws Exception {
    try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
      SecretVersionName name = SecretVersionName.of("PROJECT_ID", "pexels-key", "latest");
      AccessSecretVersionRequest req = AccessSecretVersionRequest.newBuilder().setName(name.toString()).build();
      pexelsKey = client.accessSecretVersion(req).getPayload().getData().toStringUtf8();
    }
  }
  @ProcessElement public void processElement(ProcessContext c) { /* use pexelsKey */ }
}
