interface Good {
  id: string;//
  name: string;//
  price: number;//
  picture: string;//
  desc:string;
}

type Good_Pt = Partial<Good>;
