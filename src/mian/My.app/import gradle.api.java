package com_facebook_pexel_java_Android_sdk

import javax.inject.Inject;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.problems.*;
import org.gradle.api.problems.ProblemReporter;
import org.gradle.api.problems.ProblemGroup;
import org.gradle.api.problems.ProblemId;
import org.gradle.api.problems.Severity;

public class ProblemReportingPlugin implements Plugin<Project> {

    // បង្កើត Group សម្រាប់សម្គាល់ប្រភេទបញ្ហា
    public static final ProblemGroup PROBLEM_GROUP_API =
            ProblemGroup.create("sample-group", "Sample Group");

    private final ProblemReporter problemReporter;

    // កំណត់ Interface សម្រាប់ទិន្នន័យបន្ថែម (AdditionalData)
    interface SomeData extends AdditionalData {
        void setName(String name);
        String getName();
    }

    // Constructor មាន Inject សម្រាប់ Problems API
    @Inject
    public ProblemReportingPlugin(Problems problems) {
        this.problemReporter = problems.getReporter();
    }

    @Override
    public void apply(Project project) {
        // បង្កើត ProblemId
        ProblemId problemId = ProblemId.create(
                "adhoc-deprecation",
                "Plugin 'x' is deprecated",
                PROBLEM_GROUP_API
        );

        // រាយការណ៍បញ្ហា
        this.problemReporter.report(problemId, builder -> builder
                .details("The plugin 'x' is deprecated since version 2.5")
                .solution("Please use plugin 'y'")
                .severity(Severity.WARNING)
                .additionalData(SomeData.class, additionalData -> {
                    additionalData.setName("Some name");
                })
        );

        // បង្ហាញសារនៅ Console
        project.getLogger().warn("⚠️ The plugin 'x' is deprecated — please use plugin 'y' instead.");
    }
}
