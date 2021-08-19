public class NBody{
	static double readRadius(String filename){
		double r;
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		r=in.readDouble();
		return r;
	}
	static Body[] readBodies(String filename){
		double xxpos,yypos,xxVel,yyVel,mass;
		String imgFileName;
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		Body[] b=new Body[5];
		for(int i=0;i<5;i++){
			xxpos=in.readDouble();
			yypos=in.readDouble();
			xxVel=in.readDouble();
			yyVel=in.readDouble();
			//System.out.println(yyVel);
			mass=in.readDouble();
			//System.out.println(mass);
			imgFileName=in.readString();
			b[i]=new Body(xxpos,yypos,xxVel,yyVel,mass,imgFileName);
		}
		return b;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];

		double xForce[]=new double[5];
		double yForce[]=new double[5];

		double new_t=0;
		double universe_radius;
		Body b[] = new Body [5];
		universe_radius=readRadius(filename);
		b = readBodies(filename);

		StdDraw.enableDoubleBuffering();
		while(new_t<T){
			for(int i=0;i<5;i++){
				xForce[i]=b[i].calcNetForceExertedByX(b);
				yForce[i]=b[i].calcNetForceExertedByY(b);
				b[i].update(dt,xForce[i],yForce[i]);

				
			}
			StdDraw.setScale(-universe_radius,universe_radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "D:/cs61b/CS61B/skeleton-sp19-master/proj0/images/starfield.jpg");
			for(int i=0;i<5;i++){
				b[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			new_t=new_t+dt;
		}
	}

}