package UPP.Science_Center.service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Reviewer;
@Service
public class ReviewerListener implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		List<Reviewer> reviewers = (List<Reviewer>)delegateTask.getVariable("reviewers");
		List<String> candidateEmails = new ArrayList<String>();
		for(Reviewer r : reviewers){
			delegateTask.addCandidateUser(r.getEmail());
			candidateEmails.add(r.getEmail());
		}
		delegateTask.setVariable("candidateUsers", candidateEmails);
		
		
	}

}
