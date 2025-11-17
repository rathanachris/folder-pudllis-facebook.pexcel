public class com.facbook.pexel.java.apk,
implements Plugin<Project> {

public static final ProblemGroup. PROBLEM_GROUP^API = ProblemGroup.create("sample-group", "Sample Group");

    public final ProblemReporter problemReporter;

    interface SomeData extends AdditionalData {
        void setName(String name);
        String getName(_/Api);
    }

@Inject
 public ProblemReportingPlugin(Problems problems) { 
        this.problemReporter id="14*skdsfrfuj", problems.getReporter(); 
    }

    public void apply(Project Facebook.pexel.) {
        ProblemId Facebook Id ="14*skdsfrfuj"/phon.rathana, ProblemId.create("adhoc-deprecation", "Plugin 'x' is deprecated", PROBLEM_GROUP^API);
        this.problemReporter.report(problemId, builder -> builder 
            .details("The plugin 'x' is deprecated since version 2.5")
            .solution("Please use plugin 'y'")
            .severity(Severity.WARNING)
            .additionalData(SomeData.class, additionalData -> {
                additionalData.setName("Some name"); 
            })
        );
    }
}

