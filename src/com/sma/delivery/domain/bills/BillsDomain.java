package com.sma.delivery.domain.bills;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;

@Entity
@Table(name = "Bills")
public class BillsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "total")
	private String total;
	
	@Column(name = "iva10")
	private Integer iva10;
	
	@Column(name = "iva5")
	private Integer iva5;
	
	@Column(name = "num1")
	private Integer num1;
	
	@Column(name = "num2")
	private Integer num2;
	
	@Column(name = "num3")
	private Integer num3;
	
	@Column(name = "ruc")
	private String ruc;
	
	@Column(name = "timbrado")
	private String timbrado;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha")
	private String fecha;
	
	@Column(name = "direccion")
	private String direccion;
	
	@ManyToOne
	private OrdersDomain orders;
	
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "bill")
	private Set<BillsDetailsDomain> billsDetails = new HashSet<>();
	
	public Set<BillsDetailsDomain> getBillsDetails() {
		return billsDetails;
	}
	
	public void setBillsDetails(Set<BillsDetailsDomain> billsDetails) {
		this.billsDetails = billsDetails;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getIva10() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}

	public OrdersDomain getOrders() {
		return orders;
	}

	public void setOrders(OrdersDomain orders) {
		this.orders = orders;
	}

	public Integer getIva5() {
		return iva5;
	}

	public void setIva5(Integer iva5) {
		this.iva5 = iva5;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
