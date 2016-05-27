package org.becausecucumber.eclipse.plugin.ui.syntaxcolor;

import org.becausecucumber.eclipse.plugin.cucumber.Feature;
import org.becausecucumber.eclipse.plugin.cucumber.Scenario;
import org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline;
import org.becausecucumber.eclipse.plugin.cucumber.Step;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

@SuppressWarnings("restriction")
public class SemanticHighlightingCalculator implements ISemanticHighlightingCalculator {

	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
		if (resource == null || resource.getParseResult() == null || resource.getContents().size() <= 0) {
			return;
		}
		Feature feature = (Feature) resource.getContents().get(0);
		if (feature.getBackground() != null) {
			provideHighlightingForSteps(feature.getBackground().getSteps(), acceptor);
		}
		for (Object child : feature.getScenarios()) {
			if (child instanceof Scenario) {
				Scenario scenario = (Scenario) child;
				provideHighlightingForSteps(scenario.getSteps(), acceptor);
			}
			if (child instanceof ScenarioOutline) {
				ScenarioOutline outline = (ScenarioOutline) child;
				provideHighlightingForSteps(outline.getSteps(), acceptor);
			}
		}
	}

	private void provideHighlightingForSteps(EList<Step> steps, IHighlightedPositionAcceptor acceptor) {
		for (Step step : steps) {
			INode node = NodeModelUtils.getNode(step);
			acceptor.addPosition(node.getOffset(), node.getText().trim().indexOf(" "),
					HighlightingConfiguration.STEP_KEYWORD);
			if (step.eContainer() instanceof ScenarioOutline && step.getDescription() != null) {
				this.provideHighlightingForPlaceholders(node.getText(), node, 0, acceptor);
			}
		}
	}

	private void provideHighlightingForPlaceholders(String description, INode node, int current,
			IHighlightedPositionAcceptor acceptor) {
		int start = description.indexOf('<', current);
		int stop = description.indexOf('>', start);
		if (start > 0 && stop > 0 && description.charAt(start + 1) != ' ') {
			acceptor.addPosition(node.getTotalOffset() + start, stop - start + 1,
					HighlightingConfiguration.PLACEHOLDER);
			this.provideHighlightingForPlaceholders(description, node, stop + 1, acceptor);
		}
	}

}
