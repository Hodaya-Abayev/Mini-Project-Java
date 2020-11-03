/**
 * 
 */
package Primitives;

/**
 * @author осас
 *
 */
public class Material {
	/*
	 * diffuse
	 */
double _kd;
/*
 * specular
 */
double _ks;
/*
 * refraction
 */
double _kt;
/*
 * reflection
 */
double _kr;
int _nShiness;
	/**
	 * constructor
	 */
	public Material(double kd,double ks,int shine) 
	{
		this(kd,ks,shine, 0,0);
	}
	/**
	 * @return the _kt
	 */
	public double get_kt() {
		return _kt;
	}
	/**
	 * @return the _kr
	 */
	public double get_kr() {
		return _kr;
	}
	/*
	 * constructor with kr and kt
	 */
	public Material(double kd,double ks,int shine,double kt,double kr)
	{
		_kd=kd;
		_ks=ks;
		_nShiness=shine;
		_kt=kt;
		_kr=kr;
	}
	
	
	/**
	 * @return the _kd
	 */
	public double get_kd() {
		return _kd;
	}
	/**
	 * @return the _ks
	 */
	public double get_ks() {
		return _ks;
	}
	/**
	 * @return the _nShiness
	 */
	public int get_nShiness() {
		return _nShiness;
	}

}
