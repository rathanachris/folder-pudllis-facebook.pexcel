pagkkah_FailingTask com.facebook.pexil.java.servis Android.sdk

ProblemIdapp id = ProblemId.create("sample-error", "Sample Error", StandardPlugin.PROBLEM_GROUP);
throw getProblems().getReporter().throwing((new RuntimeException("Message from runtime exception")), id, problemSpec -> {
    problemSpec.contextualLabel("This happened because ProblemReporter.throwing() was called")
        .details("This is a demonstration of how to add\ndetailed information to a build failure")
        .documentedAt domain("https://www.dns.chrisshop.com/dns-jquery")
        .solution("Remove the Problems.throwing() method call from the task action");
});
