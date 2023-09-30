package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservationDao;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reservation;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.ParseException;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		///

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT r FROM Reservation r";
		Query query = em.createQuery(jpql, Reservation.class);

		List<Reservation> resultList = query.getResultList();

		for (Reservation reservation : resultList) {
			Object[] data = { reservation.getId().toString(), reservation.getFechaEntrada().toString(),
					reservation.getFechaSalida().toString(), String.valueOf(reservation.getValor()),
					reservation.getFormaPago() };
			modelo.addRow(data);
		}

		em.getTransaction().commit();
		em.close();

		// -- List every element of every table -- //

		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		String jpql2 = "SELECT h FROM Huesped h";
		Query query2 = em2.createQuery(jpql2, Huesped.class);

		List<Huesped> resultList2 = query2.getResultList();

		modeloHuesped.setRowCount(0);

		for (Huesped huesped : resultList2) {
			Object[] data = { huesped.getId().toString(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFecha_nacimiento().toString(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getReserva_id() };
			modeloHuesped.addRow(data);
		}
		em2.getTransaction().commit();
		em2.close();

		//

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText() != null) {
					EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
					EntityManager em = emf.createEntityManager();
					if (isNumeric(txtBuscar.getText())) {
						em.getTransaction().begin();
						String jpql = "SELECT r FROM Reservation r WHERE r.id = :givenId";
						Query query = em.createQuery(jpql, Reservation.class);

						Long txtBuscarConverted = Long.parseLong(txtBuscar.getText());
						query.setParameter("givenId", txtBuscarConverted);

						List<Reservation> resultList = query.getResultList();

						modelo.setRowCount(0);

						for (Reservation reservation : resultList) {
							Object[] data = { reservation.getId().toString(), reservation.getFechaEntrada().toString(),
									reservation.getFechaSalida().toString(), String.valueOf(reservation.getValor()),
									reservation.getFormaPago() };
							modelo.addRow(data);
						}

						em.getTransaction().commit();
						em.close();
					} else {
						em.getTransaction().begin();
						String jpql = "SELECT h FROM Huesped h WHERE h.apellido = :givenApellido";
						Query query = em.createQuery(jpql, Huesped.class);

						query.setParameter("givenApellido", txtBuscar.getText());

						List<Huesped> resultList = query.getResultList();

						modeloHuesped.setRowCount(0);

						for (Huesped huesped : resultList) {
							Object[] data = { huesped.getId().toString(), huesped.getNombre(), huesped.getApellido(),
									huesped.getFecha_nacimiento().toString(), huesped.getNacionalidad(),
									huesped.getTelefono(), huesped.getReserva_id() };
							modeloHuesped.addRow(data);
						}
						em.getTransaction().commit();
						em.close();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateRows();
			}

		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteRows();
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void deleteRows() {
		int selectedRow = tbReservas.getSelectedRow();
		int selectedRowHuesped = tbHuespedes.getSelectedRow();
		
		if (selectedRow != -1 && selectedRowHuesped == -1) {
			String value = (String) tbReservas.getValueAt(selectedRow, 0);
            Long reservaId = Long.parseLong(value);
            ReservationDao reservationDao = new ReservationDao();
            reservationDao.delete(reservaId);
            modelo.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Se ha eliminado la reserva con ID " + reservaId.toString());
            
        } else if (selectedRow == -1 && selectedRowHuesped == -1){
            JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada en ninguna tabla.");
        }
		
        else if (selectedRowHuesped != -1 && selectedRow == -1) {
			String value = (String) tbHuespedes.getValueAt(selectedRowHuesped, 0);
            Long huespedId = Long.parseLong(value);
            HuespedDao huespedDao = new HuespedDao();
            huespedDao.delete(huespedId);
            modeloHuesped.removeRow(selectedRowHuesped);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el huesped con ID " + huespedId.toString());
        }
        else if(selectedRowHuesped != -1 && selectedRow != -1) {
            JOptionPane.showMessageDialog(null, "Solo puedes eliminar una fila de una sola tabla a la vez.");
        }
	}
	public void updateRows() {
		int selectedRow = tbReservas.getSelectedRow();
		int selectedRowHuesped = tbHuespedes.getSelectedRow();
		
		if (selectedRow != -1 && selectedRowHuesped == -1) {
			String id = (String) tbReservas.getValueAt(selectedRow, 0);
			String fechaEntrada = (String) tbReservas.getValueAt(selectedRow, 1);
			String fechaSalida = (String) tbReservas.getValueAt(selectedRow, 2);
			String valorString = (String) tbReservas.getValueAt(selectedRow, 3);
			Double valor = Double.parseDouble(valorString);
			String forma_pago = (String) tbReservas.getValueAt(selectedRow, 4);
			
            Long reservaId = Long.parseLong(id);
            ReservationDao reservationDao = new ReservationDao();
            try {
				reservationDao.update(reservaId, fechaEntrada, fechaSalida, valor, forma_pago);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(null, "Se ha actualizado la reserva con ID " + reservaId.toString());
            
        } else if (selectedRow == -1 && selectedRowHuesped == -1){
            JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada en ninguna tabla.");
        }
		
        else if (selectedRowHuesped != -1 && selectedRow == -1) {
        	String id = (String) tbHuespedes.getValueAt(selectedRowHuesped, 0);
			String nombre = (String) tbHuespedes.getValueAt(selectedRowHuesped, 1);
			String apellido = (String) tbHuespedes.getValueAt(selectedRowHuesped, 2);
			String fecha_nacimiento = (String) tbHuespedes.getValueAt(selectedRowHuesped, 3);
			String nacionalidad = (String) tbHuespedes.getValueAt(selectedRowHuesped, 4);
			String telefono = (String) tbHuespedes.getValueAt(selectedRowHuesped, 5);
			Long reservaId = (Long) tbHuespedes.getValueAt(selectedRowHuesped, 6);
			
            Long huespedId = Long.parseLong(id);
            HuespedDao huespedDao = new HuespedDao();
            
            try {
            	huespedDao.update(huespedId, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reservaId);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(null, "Se ha actualizado el huesped con ID " + huespedId.toString());
        }
        else if(selectedRowHuesped != -1 && selectedRow != -1) {
            JOptionPane.showMessageDialog(null, "Solo puedes editar una fila de una sola tabla a la vez.");
        }
	}

}
