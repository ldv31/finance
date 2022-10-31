package org.finance;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import gui.AssociationDialog;
import gui.ComboBoxCmdPanel;
import gui.ComboBoxSubCmd;
import gui.ComboSubCmdInterface;
import gui.ComboboxSubCmdBarPanel;
import gui.DatePickerCmdPanel;
import gui.FileMgtPanel;
import gui.InfoBar;
import gui.OperationTablePanel;
import model.CategoriesList;
import model.CategoryType;
import model.Operation;
import model.OperationCategory;
import model.PanelType;
import model.StatDataHistory;
import model.StatDataType;
import model.StatType1;


/**
 * Class allowing to display information and interact with enduser
 * @author Ludovic.duval
 * @version 1.0
 */


public class Console extends JFrame implements ComboSubCmdInterface{
	
	private OperationStatistics opStat;
	private JPanel panel = new JPanel();
	
	// variable for windows size
	private int chartPanelwidth;
	private int chartPanelwidthHistory;
	private int chartPanelHeight;
	
	private Image icon = Toolkit.getDefaultToolkit().getImage("LittleWitch.png");  
  
	// Combo box for submenu
	ComboBoxSubCmd boxCmd1;
	ComboBoxSubCmd boxCmd2;
	
	// Date for "current month" panel
	Date currentMonthDate =new Date();
	
	// dialog for setting the libelle for new association
	AssociationDialog assoDiag;
	
	
	public AssociationDialog getAssoDiag() {
		return assoDiag;
	}


	public Console(OperationStatistics opStat) {
		
		//Computed statistics from the list of operations
		this.opStat = opStat;
		
		// Instantiate dialog for association
		assoDiag = new AssociationDialog(this);
		
		
		// set the title of the frame
		setTitle("Little Witch Budget");
				
		//set frame to max screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		// change icon 
		setIconImage(icon);
		
		// set panel default size;
		chartPanelwidth = (screenSize.width / 2);
		chartPanelwidthHistory = screenSize.width;
		chartPanelHeight = screenSize.height / 2;
		
		//type of layout for the panel
		panel.setLayout(new GridBagLayout());  
		
		// Set panel background color
		panel.setBackground(Color.BLACK);
			
		//set initial panel
		setGlobalStatPanel ();
		
		// add to the frame the panel to display
		setContentPane(panel);

		// Display window
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	

	// set the  date for "current month" panel
	public void setCurrentMonthDate(Date dateCurrent) {
		currentMonthDate = dateCurrent;
	}
	
	
	// Process the combox box selection for main command (called by the combox box)
	public void processCmd(String cmd) {
		if(cmd.equals(PanelType.GLOBAL.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.GLOBAL.getTxtType());
        	remove(panel);  
        	setGlobalStatPanel ();
        	setContentPane(panel);
        	repaint(); 
        }              	
        else if(cmd.equals(PanelType.HISTORY.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.HISTORY.getTxtType());
        	remove(panel);
        	setHistoryStatPanel(opStat.getCategoriesList().get(0).getName(), opStat.getCategoriesList().get(0).getName());
        	setContentPane(panel);
        	repaint();           	
        } else if (cmd.equals(PanelType.GAINLOSS.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.GAINLOSS.getTxtType());
        	remove(panel);  
        	setGainLossPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.BUDGET.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.BUDGET.getTxtType());
        	remove(panel);  
        	setBudgetPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.CURRENT.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.CURRENT.getTxtType());
        	remove(panel);  
        	setCurrentPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.NOASSOCIATION.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.NOASSOCIATION.getTxtType());
        	remove(panel);  
        	setNoAssociationPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.AMAZON.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.AMAZON.getTxtType());
        	remove(panel);  
        	setAmazonPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.STATSPERCAT.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.STATSPERCAT.getTxtType());
        	remove(panel);  
        	setStatsPerCategoryPanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.STATSPERCATVSINCOME.getTxtType())) {
        	LogManager.LOGGER.log(Level.INFO,PanelType.STATSPERCATVSINCOME.getTxtType());
        	remove(panel);  
        	setStatsPerCategoryVSIncomePanel();
        	setContentPane(panel);
        	repaint();   
        } else if (cmd.equals(PanelType.STATSPERCATANDHISTORY.getTxtType())) {
	     	LogManager.LOGGER.log(Level.INFO,PanelType.STATSPERCATANDHISTORY.getTxtType());
	     	remove(panel);  
	     	setStatsPerCategoryAndHistory();
	     	setContentPane(panel);
	     	repaint();   
	    } else if (cmd.equals(PanelType.BUDGETVSREIMBURSEMENT.getTxtType())) {
	     	LogManager.LOGGER.log(Level.INFO,PanelType.BUDGETVSREIMBURSEMENT.getTxtType());
	     	remove(panel);  
	     	setBudgetcsReimbursement();
	     	setContentPane(panel);
	     	repaint();   
	    } else if (cmd.equals(PanelType.STATSPERCATANDHISTORYANDCONSTRAINT.getTxtType())) {
	     	LogManager.LOGGER.log(Level.INFO,PanelType.STATSPERCATANDHISTORYANDCONSTRAINT.getTxtType());
	     	remove(panel);  
	     	setStatsPerCategoryAndHistoryAndConstraint();
	     	setContentPane(panel);
	     	repaint();   
	    }	
	}
	
	
	
	// Process the combox box selection for sub  command (called by the combox box)
		public void processSubCmd(String cmd1, String cmd2) {   
            remove(panel);  
            setHistoryStatPanel(cmd1, cmd2);
        	setContentPane(panel);
        	repaint(); 		
		}
	
	
	
	// generate the command section of the Panel 
	private void generateCmd (int gridx, int gridy, PanelType pType, String opCategorieUserChoice1, String opCategorieUserChoice2) {
		GridBagConstraints gbc = new GridBagConstraints ();	
		int indexOpCategorieUserChoice1 = 0 ;
		int indexOpCategorieUserChoice2 = 0 ;
		
		// Ajout de la combo box principale
		ComboBoxCmdPanel boxCmd = new ComboBoxCmdPanel(PanelType.getTypeTextArray(), PanelType.getIndex(pType.getTxtType()), this);
		
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		// add the combo box and parameters to the panel
		panel.add(boxCmd,gbc);
		
		// si panel historique, affichage de deux combobox catégory
		
		if (pType == PanelType.HISTORY) {
			//
			// Deux combox affichables sur la page
			//
			String[] strForChoice2 = new String[opStat.getCategoriesList().size()];
			int i = 0;
			for (OperationCategory opCat : opStat.getCategoriesList()) {
				if (opCat.getName() == opCategorieUserChoice1) {
					indexOpCategorieUserChoice1 = i;	
				}
				if (opCat.getName() == opCategorieUserChoice2) {
					indexOpCategorieUserChoice2 = i;	
				}
				strForChoice2[i++] = opCat.getName();
				
			}
									
			gbc.gridx = gridx+1;
			gbc.gridy = gridy;
			gbc.gridwidth = 1;
			
			
			ComboboxSubCmdBarPanel cbbar = new ComboboxSubCmdBarPanel (strForChoice2, indexOpCategorieUserChoice1, indexOpCategorieUserChoice2, this);
			panel.add(cbbar,gbc);
						
		}
		
		// si panel "mois courant", ajouter le "date picker"
		if (pType == PanelType.CURRENT) {
			
			DatePickerCmdPanel datePicker = new DatePickerCmdPanel(this);
			
			gbc.gridx = gridx+1;
			gbc.gridy = gridy;
			gbc.gridwidth = 1;
			
			panel.add(datePicker,gbc);
			
		}
		
		// si panel "sans association", ajouter le bouton de sauvegarde
		if (pType == PanelType.NOASSOCIATION) {
			
			FileMgtPanel fileMgt = new FileMgtPanel(opStat.getOpBook(), opStat);
			
			gbc.gridx = gridx+1;
			gbc.gridy = gridy;
			gbc.gridwidth = 1;
			
			panel.add(fileMgt,gbc);
			
		}
		
		
		// si panel "sans association", ajouter le bouton de sauvegarde
		if (pType == PanelType.AMAZON) {
			
			FileMgtPanel fileMgt = new FileMgtPanel(opStat.getOpBook(), opStat);
			
			gbc.gridx = gridx+1;
			gbc.gridy = gridy;
			gbc.gridwidth = 1;
			
			panel.add(fileMgt,gbc);
			
		}
		
	}
	
	
	// Create a panel for a specific type of data and add it to the frame to the position specified
	private void generatePanel(CategoryType cType, StatDataType sType,int gridx, int gridy, int gwidth, String opCategorieUserChoice) {
		
		GridBagConstraints gbc = new GridBagConstraints ();		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultPieDataset pieDataSet = new DefaultPieDataset();
		JFreeChart barChart = null;
		JFreeChart pieChart = null;
		JFreeChart stackedBarChart = null;
		ArrayList<Operation> opAmazon = new ArrayList<Operation>();
		ChartPanel cPanel = null;
		OperationTablePanel opTablePane = new OperationTablePanel();
		String sTypeString = "";
		
		
		switch (cType) {
			
		case CREDIT:
			for (StatType1 lOpStat : opStat.getlStat()) {
				if (lOpStat.getcType() == CategoryType.CREDIT) {
					switch (sType) {
						case CATEGORY_SUM:
							dataset.addValue(lOpStat.getOpValue(), "Catégories", lOpStat.getOpCategory());
							sTypeString = StatDataType.CATEGORY_SUM.getTxtType();
						break;
						
						case CATEGORY_AVERAGE:
							dataset.addValue(-lOpStat.getOpMoyenneMensuelle(), "Catégories", lOpStat.getOpCategory());
							sTypeString = StatDataType.CATEGORY_AVERAGE.getTxtType();
							break;
							
						default:
							break;
					}
				}
				
			}
			barChart = ChartFactory.createBarChart3D(CategoryType.CREDIT.getTxtType() + " - " + sTypeString, "","Montant (€)", dataset, PlotOrientation.VERTICAL, true, true, false);
			
			formatBarChart(barChart);
			
			cPanel = new ChartPanel(barChart, chartPanelwidth, chartPanelHeight, chartPanelwidth, chartPanelHeight, chartPanelwidth, chartPanelHeight, false, false,false,false,false,false);
			break;
			
		case DEBIT:
			
			for (StatType1 lOpStat : opStat.getlStat()) {
				if (lOpStat.getcType() == CategoryType.DEBIT)
					switch (sType) {
						case CATEGORY_SUM:
							dataset.addValue(Math.abs(lOpStat.getOpValue()), "Catégories", lOpStat.getOpCategory());
							sTypeString = StatDataType.CATEGORY_SUM.getTxtType();
						break;
						
						case CATEGORY_AVERAGE:
							dataset.addValue(lOpStat.getOpMoyenneMensuelle(), "Catégories", lOpStat.getOpCategory());
							sTypeString = StatDataType.CATEGORY_AVERAGE.getTxtType();
							break;
							
						case CATEGORY_HISTORY:
							if (lOpStat.getOpCategory().equals(opCategorieUserChoice)) {
								for (StatDataHistory sDataHistory : lOpStat.getDataHistory()) {
									dataset.addValue(Math.abs(sDataHistory.getValue()), sDataHistory.getMonthAndYearShort(),"");
								}
							}
							sTypeString = StatDataType.CATEGORY_HISTORY.getTxtType() + " : " + opCategorieUserChoice;
							break;
							
						case CATEGORY_GAINLOSS:
							break;
							
						default:
							break;					
				}
			}
			barChart = ChartFactory.createBarChart3D(CategoryType.DEBIT.getTxtType() + " - " + sTypeString, "","Montant (€)", dataset, PlotOrientation.VERTICAL, true, true, false);
			
			formatBarChart(barChart);
			
			cPanel = new ChartPanel(barChart, chartPanelwidth, chartPanelHeight, chartPanelwidth, chartPanelHeight, chartPanelwidth, chartPanelHeight, false, false,false,false,false,false);
			break;
		
		case TOUS:
			switch (sType) {
				case CATEGORY_SUM:
				break;
				
				case CATEGORY_AVERAGE:
				break;
				
				case CATEGORY_GAINLOSS:		
					StatType1 lOpStatGlobal = opStat.getlStatGlobal().get(0);
					for (StatDataHistory sDataHistory : lOpStatGlobal.getDataHistory()) {
						dataset.addValue(sDataHistory.getValue(), StatDataType.CATEGORY_GAINLOSS.getTxtType(), sDataHistory.getMonthAndYearShort());
					}	
					sTypeString = StatDataType.CATEGORY_GAINLOSS.getTxtType();
					break;
				
				case CATEGORY_GAINLOSS_AVERAGE:
					StatType1 lOpStatGlobalA = opStat.getlStatGlobal().get(0);
					double averageSum = 0;
					double averageValue = 0;
					int index = 1;
					for (StatDataHistory sDataHistory : lOpStatGlobalA.getDataHistory()) {
						averageSum += sDataHistory.getValue();
						averageValue = averageSum / index;
						dataset.addValue(averageValue, StatDataType.CATEGORY_GAINLOSS_AVERAGE.getTxtType(), sDataHistory.getMonthAndYear());
						index++;
					}	
					sTypeString = StatDataType.CATEGORY_GAINLOSS_AVERAGE.getTxtType();
					break;	
					
				case CATEGORY_HISTORY:
					for (StatType1 lOpStat : opStat.getlStat()) {
						if (lOpStat.getOpCategory().equals(opCategorieUserChoice)) {
							for (StatDataHistory sDataHistory : lOpStat.getDataHistory()) {
								dataset.addValue(Math.abs(sDataHistory.getValue()), StatDataType.CATEGORY_HISTORY.getTxtType(), sDataHistory.getMonthAndYearShort());
							}
							sTypeString = lOpStat.getcType().getTxtType() + " - " + StatDataType.CATEGORY_HISTORY.getTxtType() + " : " + opCategorieUserChoice;
						}				
					}
				break;
				
				case CATEGORY_BUDGET:
					StatType1 lOpStatGlobalB = opStat.getlStatGlobal().get(1);
					for (StatDataHistory sDataHistory : lOpStatGlobalB.getDataHistory()) {
						// le "-" devant "sDataHistory.getValue()" permet de redresser le graphique pour une meilleure représentation graphique
						dataset.addValue(-sDataHistory.getValue(), StatDataType.CATEGORY_BUDGET.getTxtType() , sDataHistory.getMonthAndYearShort());
						
					}	
					sTypeString = StatDataType.CATEGORY_BUDGET.getTxtType();
					break;
					
				
				// La moyenne intègre les dépenses - les remboursements
				case CATEGORY_BUDGET_AVERAGE:
					StatType1 lOpStatGlobalC = opStat.getlStatGlobal().get(1);
					StatType1 lOpStatReimbursementB = opStat.getlStatGlobal().get(3);
					double averageSumC = 0;
					double averageValueC = 0;
					int indexC = 1;
					for (StatDataHistory sDataHistory : lOpStatGlobalC.getDataHistory()) {	
						averageSumC += sDataHistory.getValue();
						
						//check if for his date there is a reimbursement, if yes add it
						for (StatDataHistory sDataHistoryB : lOpStatReimbursementB.getDataHistory()) {
							if (sDataHistoryB.getMonthAndYearShort().equals(sDataHistory.getMonthAndYearShort())) {
								averageSumC += sDataHistoryB.getValue();
							}
						}
						
						averageValueC = averageSumC / indexC;
						dataset.addValue(Math.abs(averageValueC), StatDataType.CATEGORY_BUDGET_AVERAGE.getTxtType(), sDataHistory.getMonthAndYearShort());
						indexC++;
		
						
						
					}	
					sTypeString = StatDataType.CATEGORY_BUDGET_AVERAGE.getTxtType();
					break;	
				
				// Creation du data set pour le mois courant
				case CATEGORY_CURRENT:
					// loop sur toutes les catégories
					for (StatType1 lOpStat : opStat.getlStat()) {
						// si la catégorie est de type Débit
						if (lOpStat.getcType() == CategoryType.DEBIT) {	
							// verifie si l'historique de la catégorie est non vide
							if (!lOpStat.getDataHistory().isEmpty()) {
								// ajouter si une  valeur historique de la catégorie est dans le mois/année selectionné
								// récupérer la date selectionnée (dans date picker du pannel "Mois courant")
								Date currentDate = currentMonthDate;
												
								// cherche dans l'historique de la catégorie s'il y a couple mois/année correspondant
								for (StatDataHistory sDataHistory : lOpStat.getDataHistory()) {
									if ((currentDate.getMonth() == sDataHistory.getMonthAndYear().getMonth()) &&
											currentDate.getYear() == sDataHistory.getMonthAndYear().getYear()) {
										//Ajouter la valeur du dernier mois
										dataset.addValue(-sDataHistory.getValue(), "Catégories", lOpStat.getOpCategory());
									}		
								}		
							} else {
								dataset.addValue(0, "Catégories", lOpStat.getOpCategory());
							}
							
						}	
					}		
					sTypeString = StatDataType.CATEGORY_CURRENT.getTxtType();
					break;
				
				// Creation du dataset pour l'affichages des opérations sans associations
				case CATEGORY_NOASSOCIATION:
					 						
					sTypeString = StatDataType.CATEGORY_NOASSOCIATION.getTxtType();
					break;
					
					// Creation du dataset pour l'affichages des opérations sans associations
				case CATEGORY_AMAZON:
						
												
					for (Operation opLocal : opStat.getOpBook().getOpBookData()) {
						if (opLocal.getAssociation().equals(CategoriesList.AMAZON.getTxtType()) ) {
							opAmazon.add(opLocal);
						}					   
					}
					 						
					sTypeString = StatDataType.CATEGORY_AMAZON.getTxtType();
					break;
					
					
				case CATEGORY_STATSPERCAT:
					for (StatType1 lOpStat : opStat.getlStat()) {
						if (lOpStat.getcType() == CategoryType.DEBIT) {
							if (!lOpStat.getOpCategory().equals(CategoriesList.EPARGNE.getTxtType())) {
								String label = lOpStat.getOpCategory() + String.format( ": %.0f€,  %.2f %%", Math.abs(lOpStat.getOpValue()), 100*(Math.abs(lOpStat.getOpValue())/Math.abs(opStat.getTotalDebitSum())));
								pieDataSet.setValue(label, Math.abs(lOpStat.getOpValue()));
							}
						}
					}
					sTypeString = StatDataType.CATEGORY_STATSPERCAT.getTxtType() + String.format( "  - Total des Dépenses: %d €", opStat.getTotalDebitSum());
					break;
							
				case CATEGORY_STATSPERCATVSINCOME:
					for (StatType1 lOpStat : opStat.getlStat()) {
						if (lOpStat.getcType() == CategoryType.DEBIT) {
							if (!lOpStat.getOpCategory().equals(CategoriesList.EPARGNE.getTxtType())) {
								String label = lOpStat.getOpCategory() + String.format( ": %.0f€,  %.2f %%", Math.abs(lOpStat.getOpValue()), 100*(Math.abs(lOpStat.getOpValue())/Math.abs(opStat.getTotalIncomeSum())));
								pieDataSet.setValue(label, Math.abs(lOpStat.getOpValue()));
							}
						}
					}
					sTypeString = StatDataType.CATEGORY_STATSPERCATVSINCOME.getTxtType() + String.format( "  - Total des Revenus: %d €", opStat.getTotalIncomeSum());
					break;
					
					
				case CATEGORY_STATSPERCATANDHISTORY:
					for (StatType1 lOpStat : opStat.getlStat()) {
						if (lOpStat.getcType() == CategoryType.DEBIT) {
							if (!lOpStat.getOpCategory().equals(CategoriesList.EPARGNE.getTxtType())) {
								for (StatDataHistory sDataHistory : lOpStat.getDataHistory()) {
										dataset.addValue(Math.abs(sDataHistory.getValue()), lOpStat.getOpCategory(), sDataHistory.getMonthAndYearShort());
										System.out.println(lOpStat.getOpCategory() + "  " + sDataHistory.getMonthAndYearShort());
									}
							}	
						}
							
						sTypeString = "Budget - " + StatDataType.CATEGORY_STATSPERCATANDHISTORY.getTxtType();				
					}
					break;
					
					
				case CATEGORY_STATSPERCATANDHISTORYANDCONSTRAINT:
					for (StatType1 lOpStat : opStat.getlStat()) {
						if (lOpStat.getcType() == CategoryType.DEBIT) {
							if (lOpStat.isConstraint()) {
								for (StatDataHistory sDataHistory : lOpStat.getDataHistory()) {
										dataset.addValue(Math.abs(sDataHistory.getValue()), lOpStat.getOpCategory(), sDataHistory.getMonthAndYearShort());
										System.out.println(lOpStat.getOpCategory() + "  " + sDataHistory.getMonthAndYearShort());
									}
							}	
						}
							
						sTypeString = "Budget - " + StatDataType.CATEGORY_STATSPERCATANDHISTORYANDCONSTRAINT.getTxtType();				
					}
					break;
					
				case CATEGORY_BUDGETVSREIMBURSEMENT:
					StatType1 lOpStatReimbursemsent = opStat.getlStatGlobal().get(3);
					for (StatDataHistory sDataHistory : lOpStatReimbursemsent.getDataHistory()) {
						// le math.abs devant "sDataHistory.getValue()" permet de redresser le graphique pour une meilleure représentation graphique
						dataset.addValue(Math.abs(sDataHistory.getValue()), StatDataType.CATEGORY_BUDGETVSREIMBURSEMENT.getTxtType() , sDataHistory.getMonthAndYearShort());
						
					}	
					sTypeString = StatDataType.CATEGORY_BUDGETVSREIMBURSEMENT.getTxtType();
					break;
					
			}
			
			if (sType == StatDataType.CATEGORY_NOASSOCIATION) {
				//set data
				opTablePane.setData(opStat.getOpBook().getOpBookDataWithoutAssociation(), opStat.getOpBook().getbString(), this);
			} else if (sType == StatDataType.CATEGORY_AMAZON) {
				opTablePane.setData(opAmazon, opStat.getOpBook().getbString(), this);
			} else if ((sType == StatDataType.CATEGORY_STATSPERCAT) || (sType == StatDataType.CATEGORY_STATSPERCATVSINCOME)) {
				pieChart = ChartFactory.createPieChart3D(sTypeString, pieDataSet, false, true, false);
				
				// formating title
			    TextTitle t = pieChart.getTitle();			    
			    t.setPaint(Color.WHITE);
			    t.setFont(new Font("Arial", Font.PLAIN, 20));
				
			    // formatting background colors
			    pieChart.setBackgroundPaint(Color.BLACK);
			    
			    PiePlot plot = (PiePlot) pieChart.getPlot();
				plot.setBackgroundPaint(Color.BLACK);
				
				// formating label
				// customise the section label appearance
			    plot.setLabelFont(new Font("Arial", Font.BOLD, 14));
			    plot.setLabelLinkPaint(Color.WHITE);
			    plot.setLabelLinkStroke(new BasicStroke(2.0f));
			    plot.setLabelOutlineStroke(null);
			    plot.setLabelPaint(Color.WHITE);
			    plot.setLabelBackgroundPaint(Color.BLACK);
				
				
				cPanel = new ChartPanel(pieChart, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, false, false,false,false,false,false);
			} else if ((sType == StatDataType.CATEGORY_STATSPERCATANDHISTORY) || (sType == StatDataType.CATEGORY_STATSPERCATANDHISTORYANDCONSTRAINT)) {
				stackedBarChart = ChartFactory.createStackedBarChart(sTypeString, "","Montant (€)", dataset, PlotOrientation.VERTICAL, true, true, false);
				
				formatBarChart(stackedBarChart); 
				
				cPanel = new ChartPanel(stackedBarChart, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, false, false,false,false,false,false);			
			} else {
				barChart = ChartFactory.createBarChart3D(sTypeString, "","Montant (€)", dataset, PlotOrientation.VERTICAL, true, true, false);
				
				formatBarChart(barChart);        
				
				cPanel = new ChartPanel(barChart, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, chartPanelwidthHistory, chartPanelHeight, false, false,false,false,false,false);			
			}
			
			break;	
			
		default:
			break;
		}
			
							
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gwidth;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
				
		if ((sType != StatDataType.CATEGORY_NOASSOCIATION) && (sType != StatDataType.CATEGORY_AMAZON)) {
			panel.add(cPanel,gbc);
		}
		else {	
			panel.add(opTablePane,gbc);
		}
		
	}
	
	private void generateInfoBar(int gridx, int gridy, int gwidth) {
	
		
		GridBagConstraints gbc = new GridBagConstraints ();	 	
		InfoBar infoBar= new InfoBar(opStat);
		
		gbc.gridwidth = gwidth;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.anchor = GridBagConstraints.LINE_START;
		//gbc.weightx = 1;
		
		panel.add(infoBar,gbc);
		
		
	}
	
	
	// Panel présentant les dépenses et recettes cummulées et par catégorie
	private void setGlobalStatPanel () {
		
		panel.removeAll();
		
		generateCmd(0,0,PanelType.GLOBAL,"","");
		
		generatePanel(CategoryType.DEBIT, StatDataType.CATEGORY_SUM, 0, 1, 1, null);
		generatePanel(CategoryType.CREDIT,StatDataType.CATEGORY_SUM, 0, 2, 1, null);
		generatePanel(CategoryType.DEBIT, StatDataType.CATEGORY_AVERAGE, 1, 1, 1, null);
		generatePanel(CategoryType.CREDIT,StatDataType.CATEGORY_AVERAGE, 1, 2, 1, null);
		generateInfoBar(0,3,2);
	}
	
	// Panel présentant l'historique total et par catégorie
	private void setHistoryStatPanel (String opCategory1, String opCategory2) {
				
		panel.removeAll();
		
		generateCmd(0,0,PanelType.HISTORY, opCategory1, opCategory2);
		
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_HISTORY, 0, 2, 2, opCategory1);
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_HISTORY, 0, 3, 2, opCategory2);	
		generateInfoBar(0,4,2);
	}
	
	// Panel Gain et perte au total
	private void setGainLossPanel () {
				
		panel.removeAll();
		
		generateCmd(0,0,PanelType.GAINLOSS,"","");
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_GAINLOSS, 0, 1, 2, null);
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_GAINLOSS_AVERAGE, 0, 2, 2, null);
		generateInfoBar(0,3,2);
	}	
	
	// Budget pannel = list  of expenses with hstory
	private void setBudgetPanel () {
					
		panel.removeAll();
		
		generateCmd(0,0,PanelType.BUDGET,"","");
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_BUDGET, 0, 1, 2, null);
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_BUDGET_AVERAGE, 0, 2, 2, null);
		generateInfoBar(0,3,2);
	}
		
		
	// Budget pannel = list  of expenses with hstory
	private void setCurrentPanel () {
					
		panel.removeAll();
		
		generateCmd(0,0,PanelType.CURRENT,"","");
		
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_CURRENT, 0, 1, 2, null);
		generateInfoBar(0,2,2);
	}
	
	
	// Budget pannel = list  of expenses with hstory
	private void setNoAssociationPanel () {
					
		panel.removeAll();
		
		generateCmd(0,0,PanelType.NOASSOCIATION,"","");
		
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_NOASSOCIATION, 0, 1, 2, null);
		generateInfoBar(0,2,2);
	}
	
	// Budget pannel = list  of expenses with hstory
	private void setAmazonPanel () {
						
			panel.removeAll();
			
			generateCmd(0,0,PanelType.AMAZON,"","");
			
			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_AMAZON, 0, 1, 2, null);
			generateInfoBar(0,2,2);
		}
	
	// dépenses par catégories - cummulatif - par rapport au total des dépenses
	private void setStatsPerCategoryPanel () {
						
			panel.removeAll();
			
			generateCmd(0,0,PanelType.STATSPERCAT,"","");
			
			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_STATSPERCAT, 0, 1, 2, null);
			generateInfoBar(0,2,2);
		}

	// dépenses par catégories - cummulatif - par rapport au revenus
	private void setStatsPerCategoryVSIncomePanel () {
						
			panel.removeAll();
			
			generateCmd(0,0,PanelType.STATSPERCATVSINCOME,"","");
			
			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_STATSPERCATVSINCOME, 0, 1, 2, null);
			generateInfoBar(0,2,2);
		}
		
	
	// Evolution dans le temps de la répartition des dépenses
	private void setStatsPerCategoryAndHistory () {
						
			panel.removeAll();
			
			generateCmd(0,0,PanelType.STATSPERCATANDHISTORY,"","");
			
			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_STATSPERCATANDHISTORY, 0, 1, 2, null);
			generateInfoBar(0,2,2);
		}
	
	// Evolution dans le temps de la répartition des dépenses contraintes
	private void setStatsPerCategoryAndHistoryAndConstraint () {
							
		panel.removeAll();
		
		generateCmd(0,0,PanelType.STATSPERCATANDHISTORYANDCONSTRAINT,"","");
		
		generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_STATSPERCATANDHISTORYANDCONSTRAINT, 0, 1, 2, null);
		generateInfoBar(0,2,2);
	}
	
	// Evolution dans le temps de la répartition des dépenses
	private void setBudgetcsReimbursement () {
							
			panel.removeAll();
			
			generateCmd(0,0,PanelType.BUDGETVSREIMBURSEMENT,"","");
			
			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_BUDGET, 0, 1, 2, null);

			generatePanel(CategoryType.TOUS, StatDataType.CATEGORY_BUDGETVSREIMBURSEMENT, 0, 2, 2, null);
			generateInfoBar(0,2,2);
		}

	// Formatting of barchart
	private void formatBarChart(JFreeChart barChart) {
		
		CategoryPlot plot;
		BarRenderer renderer;
		CategoryAxis xAxis;
		Color color = new Color(0, 128, 255);
		
		// set the barChart background color
		barChart.setBackgroundPaint(Color.BLACK);
		barChart.setBorderPaint(Color.BLACK);
		barChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 12));
		barChart.getTitle().setPaint(Color.white);
		
		// Set the color of the chart
		/* Get instance of CategoryPlot */
		plot = barChart.getCategoryPlot();

		// Set chart background color
		plot.setBackgroundPaint(Color.BLACK);
		
		/* Change Bar colors */
		renderer = (BarRenderer) plot.getRenderer();
		
		renderer.setSeriesPaint(0, color);
		
		// change the orientation color of the labels on X axis
        xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        xAxis.setTickLabelPaint(Color.WHITE);
	}
	
		
}


