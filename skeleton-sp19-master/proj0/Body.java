public class Body{
	double xxPos; //Its current x position
	double yyPos; //Its current y position
	double xxVel; //Its current velocity in the x direction
	double yyVel; //Its current velocity in the y direction
	double mass; //Its mass
	String imgFileName;
	public Body(double xP,double yP,double xVel,double yVel,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xVel;
		yyVel=yVel;
		mass=m;
		imgFileName=img;
	}
	public Body(Body b){
		this(b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
	}
	double caldistance(Body b){
		double r;
		r=Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos));
		return r;


	}
	double calcForceExertedBy(Body b){
		double r;
		double G=6.67*1E-11;
		double f;
		r=this.caldistance(b);
		f=G*this.mass*b.mass/(r*r);
		return f;
	}
	double calcForceExertedByX(Body b){
		double r,fx,f;
		r=this.caldistance(b);
		f=this.calcForceExertedBy(b);
		fx=f*(b.xxPos-this.xxPos)/r;
		return fx;
	}
	double calcForceExertedByY(Body b){
		double r,fy,f;
		r=this.caldistance(b);
		f=this.calcForceExertedBy(b);
		fy=f*(b.yyPos-this.yyPos)/r;
		return fy;
	}
	double calcNetForceExertedByX(Body[] b){
		double fx=0.0;
		for(int i=0;i<b.length;i++){
			if (this.equals(b[i])) {
				continue;				
			}
			fx=fx+this.calcForceExertedByX(b[i]);
		}
		return fx;

	}
	double calcNetForceExertedByY(Body[] b){
		double fy=0.0;
		for(int i=0;i<b.length;i++){
			if (this.equals(b[i])) {
				continue;				
			}
			fy=fy+this.calcForceExertedByY(b[i]);
		}
		return fy;

	}
	void update(double dt,double fx,double fy){
		double ax,ay,vx,vy,xpos,ypos;
		ax=fx/this.mass;
		ay=fy/this.mass;
		vx=this.xxVel+ax*dt;
		vy=this.yyVel+ay*dt;

		xpos=this.xxPos+vx*dt;
		ypos=this.yyPos+vy*dt;

		this.xxPos=xpos;
		this.yyPos=ypos;
		this.xxVel=vx;
		this.yyVel=vy;
		
	}
	void draw(){
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(this.xxPos, this.yyPos, "D:/cs61b/CS61B/skeleton-sp19-master/proj0/images/"+this.imgFileName);
		
	}
}