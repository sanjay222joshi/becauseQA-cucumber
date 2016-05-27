/*
 * generated by Xtext
 */
package org.becausecucumber.eclipse.plugin.ui;

import org.becausecucumber.eclipse.plugin.common.searcher.AbstractAnnotationDescriptor;
import org.becausecucumber.eclipse.plugin.ui.hyperlink.CucumberHyperlinkHelper;
import org.becausecucumber.eclipse.plugin.ui.syntaxcolor.HighlightingConfiguration;
import org.becausecucumber.eclipse.plugin.ui.syntaxcolor.LexicalHighlightingCalculator;
import org.becausecucumber.eclipse.plugin.ui.syntaxcolor.SemanticHighlightingCalculator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.SimpleResourceSetProvider;

import com.google.inject.Binder;

/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
public class CucumberUiModule extends org.becausecucumber.eclipse.plugin.ui.AbstractCucumberUiModule {

	public final static String[] STEPS = { "Given", "When", "Then", "And", "But" };

	private static final String CUCUMBER_PACKAGE = "cucumber.api.java.en";

	public CucumberUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	/*
	 * (non-Javadoc) for create the proposal insert
	 * 
	 * @see org.eclipse.xtext.ui.DefaultUiModule#
	 * configureContentProposalLabelProvider(com.google.inject.Binder)
	 */
	@Override
	public void configureContentProposalLabelProvider(Binder binder) {
		// TODO Auto-generated method stub
		super.configureContentProposalLabelProvider(binder);
	}

	@Override
	public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
		return CucumberHyperlinkHelper.class;
	}

	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return HighlightingConfiguration.class;
	}

	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return LexicalHighlightingCalculator.class;
	}

	public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return SemanticHighlightingCalculator.class;
	}

	@Override
	public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
		return BuilderParticipant.class;
	}

	/*
	 * (Not Javadoc) <p>Title: bindIResourceSetProvider</p>
	 * <p>Description:http://java.dzone.com/articles/make-your-xtext-based-
	 * editor
	 * 
	 * @return
	 * 
	 * @see org.eclipse.xtext.ui.DefaultUiModule#bindIResourceSetProvider()
	 */

	@Override
	public Class<? extends IResourceSetProvider> bindIResourceSetProvider() {
		// TODO Auto-generated method stub
		return SimpleResourceSetProvider.class;
	}

	// contributed by
	// org.eclipse.xtext.generator.builder.BuilderIntegrationFragment
	public Class<? extends org.eclipse.xtext.ui.editor.IXtextEditorCallback> bindIXtextEditorCallback() {
		return org.eclipse.xtext.builder.nature.NatureAddingEditorCallback.class;
	}

	public Class<? extends ISyntaxErrorMessageProvider> bindISyntaxErrorMessageProvider() {
		return SyntaxErrorMessageProvider.class;
	}

	public Class<? extends AbstractAnnotationDescriptor> bindAnnotationDescriptor() {
		return CucumberAnnotationDescriptor.class;
	}

	public static class CucumberAnnotationDescriptor extends AbstractAnnotationDescriptor {

		@Override
		public String[] getNames() {
			return STEPS;
		}

		@Override
		public String getPackage() {
			return CUCUMBER_PACKAGE;
		}
	}
}
