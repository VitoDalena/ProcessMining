/*      */ package org.processmining.plugins.heuristicsnet.miner.heuristics.miner.gui;
/*      */ 
/*      */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*      */ import com.fluxicon.slickerbox.factory.SlickerFactory;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map.Entry;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JViewport;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.swing.table.JTableHeader;
/*      */ import javax.swing.table.TableCellRenderer;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import org.processmining.models.heuristics.impl.HNSubSet;
/*      */ import org.processmining.plugins.heuristicsnet.miner.heuristics.miner.operators.Operator;
/*      */ import org.processmining.plugins.heuristicsnet.miner.heuristics.miner.operators.Split;
/*      */ import org.processmining.plugins.heuristicsnet.miner.heuristics.miner.operators.Stats;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class AnnotationsPanel
/*      */   extends JPanel
/*      */ {
/*      */   private static final long serialVersionUID = 6615860311124501461L;
/*      */   private String opID;
/*      */   private JLabel title;
/*      */   private JLabel none;
/*      */   private JComboBox perspective;
/*      */   private JComboBox metric;
/*      */   private JPanel annotationsPanel;
/*      */   private JScrollPane annotationsScroll;
/*      */   private JTable patterns;
/*      */   private JTable connections;
/*      */   
/*      */   public AnnotationsPanel(SlickerFactory factory, SlickerDecorator decorator, Operator op, String opID)
/*      */   {
/* 1621 */     this.opID = opID;
/*      */     
/* 1623 */     setLayout(null);
/*      */     
/* 1625 */     this.title = factory.createLabel("");
/* 1626 */     this.title.setForeground(Color.darkGray);
/* 1627 */     this.title.setFont(new Font("Dialog", 2, 18));
/*      */     
/*      */ 
/* 1630 */     this.none = factory.createLabel("None");
/* 1631 */     this.none.setForeground(Color.darkGray);
/* 1632 */     this.none.setFont(new Font("Dialog", 1, 16));
/* 1633 */     this.none.setVisible(false);
/*      */     
/* 1635 */     this.perspective = factory.createComboBox(new String[] { "Connections", "Patterns" });
/*      */     
/* 1637 */     this.perspective.setSelectedItem("Patterns");
/* 1638 */     this.perspective.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e)
/*      */       {
/* 1642 */         if (AnnotationsPanel.this.perspective.getSelectedIndex() == 1) {
/* 1643 */           AnnotationsPanel.this.annotationsScroll.setViewportView(AnnotationsPanel.this.patterns);
/*      */         } else {
/* 1645 */           AnnotationsPanel.this.annotationsScroll.setViewportView(AnnotationsPanel.this.connections);
/*      */         }
/*      */         
/*      */       }
/* 1649 */     });
/* 1650 */     this.metric = factory.createComboBox(new String[] { "Frequency" });
/* 1651 */     this.metric.setSelectedItem("Frequency");
/*      */     
/* 1653 */     this.annotationsScroll = new JScrollPane();
/* 1654 */     this.annotationsScroll.setBorder(BorderFactory.createEmptyBorder());
/*      */     
/* 1656 */     decorator.decorate(this.annotationsScroll, Color.WHITE, Color.GRAY, Color.DARK_GRAY);
/*      */     
/* 1658 */     this.annotationsScroll.getViewport().setBackground(Color.WHITE);
/*      */     
/* 1660 */     initTables(op, null);
/*      */     
/* 1662 */     this.annotationsPanel = factory.createRoundedPanel(15, Color.WHITE);
/* 1663 */     this.annotationsPanel.setLayout(null);
/* 1664 */     this.annotationsPanel.add(this.none);
/* 1665 */     this.annotationsPanel.add(this.metric);
/* 1666 */     this.annotationsPanel.add(this.annotationsScroll);
/*      */     
/* 1668 */     add(this.title);
/* 1669 */     add(this.perspective);
/* 1670 */     add(this.annotationsPanel);
/*      */     
/* 1672 */     setBackground(Color.LIGHT_GRAY);
/*      */   }
/*      */   
/*      */   public void setSize(int width, int height)
/*      */   {
/* 1677 */     super.setSize(width, height);
/*      */     
/* 1679 */     this.title.setBounds(0, 0, width, 20);
/* 1680 */     this.none.setBounds(20, 40, width - 60, 20);
/* 1681 */     this.perspective.setBounds(0, 30, width, 20);
/* 1682 */     this.annotationsPanel.setBounds(0, 60, width, height - 60);
/*      */     
/* 1684 */     this.annotationsScroll.setBounds(5, 40, width - 10, height - 105);
/* 1685 */     this.metric.setBounds(width - 105, 10, 100, 20);
/*      */   }
/*      */   
/*      */   private void initTables(Operator op, HashMap<String, String> keys)
/*      */   {
/* 1690 */     int elements = 0;
/* 1691 */     int patterns = 0;
/*      */     
/* 1693 */     if (op != null)
/*      */     {
/* 1695 */       elements = op.getElements().size();
/* 1696 */       patterns = op.getLearnedPatterns().size();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1701 */     this.patterns = new JTable();
/*      */     
/* 1703 */     this.patterns.setGridColor(Color.GRAY);
/* 1704 */     this.patterns.setBackground(Color.WHITE);
/* 1705 */     this.patterns.setSelectionBackground(Color.LIGHT_GRAY);
/* 1706 */     this.patterns.setSelectionForeground(Color.DARK_GRAY);
/* 1707 */     this.patterns.setShowVerticalLines(false);
/*      */     
/* 1709 */     final Class<?>[] classesTypes = new Class[elements + 2];
/* 1710 */     final boolean[] classesEdit = new boolean[elements + 2];
/* 1711 */     for (int i = 0; i < elements; i++)
/*      */     {
/* 1713 */       classesEdit[i] = false;
/* 1714 */       classesTypes[i] = Boolean.class;
/*      */     }
/* 1716 */     classesEdit[elements] = false;
/* 1717 */     classesTypes[elements] = String.class;
/* 1718 */     classesEdit[(elements + 1)] = false;
/* 1719 */     classesTypes[(elements + 1)] = String.class;
/*      */     
/* 1721 */     DefaultTableModel newTableModelP = new DefaultTableModel(new Object[0][], new String[0])
/*      */     {
/*      */       private static final long serialVersionUID = -3760751300047576804L;
/* 1724 */       Class<?>[] types = classesTypes;
/* 1725 */       boolean[] canEdit = classesEdit;
/*      */       
/*      */       public Class<?> getColumnClass(int columnIndex) {
/* 1728 */         return this.types[columnIndex];
/*      */       }
/*      */       
/*      */       public boolean isCellEditable(int rowIndex, int columnIndex) {
/* 1732 */         return this.canEdit[columnIndex];
/*      */       }
/*      */       
/* 1735 */     };
/* 1736 */     int sum = 0;
/* 1737 */     ArrayList<Integer> stackC = new ArrayList(elements);
/* 1738 */     for (int i = 0; i < elements; i++) {
/* 1739 */       stackC.add(new Integer(0));
/*      */     }
/* 1741 */     if (op != null)
/*      */     {
/* 1743 */       ArrayList<String> stackP = new ArrayList(patterns);
/* 1744 */       ArrayList<Integer> stackV = new ArrayList(patterns);
/* 1745 */       for (Map.Entry<String, Stats> entry : op.getLearnedPatterns().entrySet())
/*      */       {
/*      */ 
/* 1748 */         int occurrences = ((Stats)entry.getValue()).getOccurrences();
/*      */         
/* 1750 */         boolean isInserted = false;
/* 1751 */         for (int i = 0; i < stackP.size(); i++)
/*      */         {
/* 1753 */           if (occurrences > ((Integer)stackV.get(i)).intValue())
/*      */           {
/* 1755 */             stackP.add(i, entry.getKey());
/* 1756 */             stackV.add(i, Integer.valueOf(occurrences));
/* 1757 */             isInserted = true;
/* 1758 */             break;
/*      */           }
/*      */         }
/* 1761 */         if (!isInserted)
/*      */         {
/* 1763 */           stackP.add(entry.getKey());
/* 1764 */           stackV.add(Integer.valueOf(occurrences));
/*      */         }
/*      */         
/* 1767 */         sum += occurrences;
/*      */       }
/*      */       
/* 1770 */       Boolean[][] p = new Boolean[elements][patterns];
/* 1771 */       String[][] m = new String[2][patterns];
/*      */       
/* 1773 */       for (int i = 0; i < stackP.size(); i++)
/*      */       {
/* 1775 */         String code = (String)stackP.get(i);
/* 1776 */         int occurrences = ((Integer)stackV.get(i)).intValue();
/*      */         
/* 1778 */         float percentage = Math.round(occurrences / sum * 10000.0F) / 100.0F;
/*      */         
/*      */ 
/* 1781 */         for (int j = 0; j < elements; j++)
/*      */         {
/* 1783 */           if (code.charAt(j) == '1')
/*      */           {
/* 1785 */             p[j][i] = Boolean.valueOf(true);
/*      */             
/* 1787 */             Integer temp = (Integer)stackC.remove(j);
/* 1788 */             temp = Integer.valueOf(temp.intValue() + occurrences);
/* 1789 */             stackC.add(j, temp);
/*      */           } else {
/* 1791 */             p[j][i] = Boolean.valueOf(false);
/*      */           } }
/* 1793 */         m[0][i] = (" " + String.valueOf(occurrences));
/* 1794 */         m[1][i] = (percentage + "%");
/*      */       }
/*      */       
/* 1797 */       for (int i = 0; i < elements; i++) {
/* 1798 */         newTableModelP.addColumn("", p[i]);
/*      */       }
/* 1800 */       newTableModelP.addColumn("", m[0]);
/* 1801 */       newTableModelP.addColumn("", m[1]);
/*      */     }
/*      */     
/* 1804 */     TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
/*      */     
/* 1806 */     this.patterns.setModel(newTableModelP);
/*      */     
/* 1808 */     if (elements > 0)
/*      */     {
/* 1810 */       for (int i = 0; i < elements + 2; i++)
/*      */       {
/* 1812 */         TableColumn column = this.patterns.getColumnModel().getColumn(i);
/*      */         
/*      */ 
/* 1815 */         if (i < elements)
/*      */         {
/* 1817 */           String headerValue = " " + convertID((String)keys.get(String.valueOf(op.getElements().get(i))));
/*      */           
/*      */ 
/*      */ 
/* 1821 */           if (headerValue.length() > 20) {
/* 1822 */             headerValue = headerValue.substring(0, 17) + "...";
/*      */           }
/* 1824 */           column.setMinWidth(20);
/* 1825 */           column.setMaxWidth(20);
/* 1826 */           column.setHeaderValue(headerValue);
/*      */         }
/* 1828 */         column.setHeaderRenderer(headerRenderer);
/*      */       }
/* 1830 */       TableColumn column1 = this.patterns.getColumnModel().getColumn(elements);
/*      */       
/* 1832 */       TableColumn column2 = this.patterns.getColumnModel().getColumn(elements + 1);
/*      */       
/*      */ 
/* 1835 */       column1.setMinWidth(60);
/* 1836 */       column1.setMaxWidth(60);
/* 1837 */       column2.setMinWidth(50);
/* 1838 */       column2.setMaxWidth(50);
/*      */       
/* 1840 */       this.patterns.getTableHeader().setBackground(Color.WHITE);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1845 */     this.connections = new JTable();
/*      */     
/* 1847 */     this.connections.setGridColor(Color.GRAY);
/* 1848 */     this.connections.setBackground(Color.WHITE);
/* 1849 */     this.connections.setSelectionBackground(Color.LIGHT_GRAY);
/* 1850 */     this.connections.setSelectionForeground(Color.DARK_GRAY);
/* 1851 */     this.connections.setShowVerticalLines(false);
/*      */     
/* 1853 */     DefaultTableModel newTableModelC = new DefaultTableModel(new Object[0][], new String[0])
/*      */     {
/*      */       private static final long serialVersionUID = -9201456440598163559L;
/* 1856 */       Class<?>[] types = { String.class, String.class, String.class };
/*      */       
/* 1858 */       boolean[] canEdit = { false, false, false };
/*      */       
/*      */       public Class<?> getColumnClass(int columnIndex) {
/* 1861 */         return this.types[columnIndex];
/*      */       }
/*      */       
/*      */       public boolean isCellEditable(int rowIndex, int columnIndex) {
/* 1865 */         return this.canEdit[columnIndex];
/*      */       }
/*      */     };
/*      */     
/* 1869 */     if (op != null)
/*      */     {
/* 1871 */       ArrayList<Integer> stackI = new ArrayList(elements);
/* 1872 */       for (int i = 0; i < stackC.size(); i++)
/*      */       {
/* 1874 */         int value = ((Integer)stackC.get(i)).intValue();
/*      */         
/* 1876 */         boolean isInserted = false;
/* 1877 */         for (int j = 0; j < stackI.size(); j++)
/*      */         {
/* 1879 */           int temp = ((Integer)stackC.get(((Integer)stackI.get(j)).intValue())).intValue();
/*      */           
/* 1881 */           if (value > temp)
/*      */           {
/* 1883 */             stackI.add(j, new Integer(i));
/* 1884 */             isInserted = true;
/* 1885 */             break;
/*      */           }
/*      */         }
/* 1888 */         if (!isInserted)
/*      */         {
/* 1890 */           stackI.add(new Integer(i));
/*      */         }
/*      */       }
/*      */       
/* 1894 */       String[][] m = new String[3][elements];
/*      */       
/* 1896 */       for (int i = 0; i < stackC.size(); i++)
/*      */       {
/* 1898 */         int element = op.getElements().get(((Integer)stackI.get(i)).intValue());
/* 1899 */         String elementID = convertID((String)keys.get(String.valueOf(element)));
/*      */         
/* 1901 */         int occurrences = ((Integer)stackC.get(((Integer)stackI.get(i)).intValue())).intValue();
/*      */         
/* 1903 */         float percentage = Math.round(occurrences / sum * 10000.0F) / 100.0F;
/*      */         
/*      */ 
/* 1906 */         m[0][i] = elementID;
/* 1907 */         m[1][i] = String.valueOf(occurrences);
/* 1908 */         m[2][i] = (percentage + "%");
/*      */       }
/*      */       
/* 1911 */       for (int i = 0; i < 3; i++) {
/* 1912 */         newTableModelC.addColumn("", m[i]);
/*      */       }
/*      */     }
/* 1915 */     this.connections.setModel(newTableModelC);
/*      */     
/* 1917 */     if (op != null)
/*      */     {
/* 1919 */       TableColumn column1 = this.connections.getColumnModel().getColumn(1);
/*      */       
/* 1921 */       TableColumn column2 = this.connections.getColumnModel().getColumn(2);
/*      */       
/*      */ 
/* 1924 */       column1.setMinWidth(60);
/* 1925 */       column1.setMaxWidth(60);
/* 1926 */       column2.setMinWidth(50);
/* 1927 */       column2.setMaxWidth(50);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1932 */     if (this.perspective.getSelectedIndex() == 1) {
/* 1933 */       this.annotationsScroll.setViewportView(this.patterns);
/*      */     } else {
/* 1935 */       this.annotationsScroll.setViewportView(this.connections);
/*      */     }
/*      */   }
/*      */   
/*      */   public void update(Operator op, String opID, HashMap<String, String> keys) {
/* 1940 */     if (!this.opID.equals(opID))
/*      */     {
/* 1942 */       this.opID = opID;
/*      */       
/* 1944 */       String text = "";
/* 1945 */       if ((op instanceof Split)) {
/* 1946 */         text = "Outputs of " + convertID(opID);
/*      */       } else {
/* 1948 */         text = "Inputs of " + convertID(opID);
/*      */       }
/* 1950 */       this.title.setText(text);
/* 1951 */       this.title.setToolTipText(text);
/*      */       
/* 1953 */       if (op.getLearnedPatterns().isEmpty())
/*      */       {
/* 1955 */         this.none.setVisible(true);
/*      */         
/* 1957 */         this.perspective.setEnabled(false);
/*      */         
/* 1959 */         this.metric.setEnabled(false);
/* 1960 */         this.annotationsScroll.setVisible(false);
/* 1961 */         this.annotationsScroll.setEnabled(false);
/*      */       }
/*      */       else {
/* 1964 */         this.none.setVisible(false);
/*      */         
/* 1966 */         this.perspective.setEnabled(true);
/*      */         
/* 1968 */         this.metric.setEnabled(true);
/* 1969 */         this.annotationsScroll.setVisible(true);
/* 1970 */         this.annotationsScroll.setEnabled(true);
/*      */         
/* 1972 */         initTables(op, keys);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private String convertID(String id)
/*      */   {
/* 1979 */     int index = id.indexOf("+");
/*      */     
/* 1981 */     return id.substring(0, index) + " (" + id.substring(index + 1) + ")";
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/heuristicsnet/miner/heuristics/miner/gui/AnnotationsPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */