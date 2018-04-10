using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    /// <summary>
    /// поколение
    /// </summary>
    class Generation
    {

        //----------------------------------------------------
        //поля
        //----------------------------------------------------

        //размер поколения
        private int length = 0;
        public int Length
        {
            get { return length; }
            set { if (value > 0) length = value;
                else throw new Exception("Размер не может быть отрицательным");
            }
        }

        //константа, для вероятности мутации(чем больше, тем менее вероятно мутация)
        private int mutationConst;
        public int MutationConst
        {
            set { if (value > 0) mutationConst = value;
                else throw new Exception("Константа мутации должна быть положительной"); }       
        }

        // список, для следующего поколения
        private List<Individual> nextGeneration = new List<Individual>();

        // особи, состоящие в поколении
        private Individual[] individuals = new Individual[0];
        public Individual[] Individuals
        {
            get { return individuals; }
            set { individuals = value; }
        }


        //----------------------------------------------------
        //Методы
        //----------------------------------------------------

        /// <summary>
        /// производит мутацию каждой особи с вероятностью, зависящей от mutationConst
        /// </summary>
        private void Mutation()
        {
           
            foreach(var a in individuals)
            {
                if (Program.Rand.Next(1, mutationConst+1) == 1)
                    nextGeneration.Add(a.Mutate());
            }
        }

        /// <summary>
        /// возвращает номер случайного партнера
        /// </summary>
        private int getPartner()
        {
            
            int a = Program.Rand.Next(1, length * (length + 1) / 2 +1);
            return (int)Math.Round(0.4999999 + (Math.Sqrt(1+8*a)-1) / 2)-1;
        }

        /// <summary>
        /// скрещивает все особи. Для каждой особи выбирается случайный партнер, основываясь на формуле суммы чисел от 1 до n
        /// </summary>
        private void Crossingover()
        {
            int j;
            for(int i = 0; i < length; ++i)
            {
                j = i;
                while (j == i)
                    j = getPartner();
                Individual firstChild; Individual secondChild;
                Individual.Crossingover(individuals[i], individuals[j], out firstChild, out secondChild);
                nextGeneration.Add(firstChild);
                nextGeneration.Add(secondChild);
            }

        }



        /// <summary>
        /// преобразует текущее поколение в следующее
        /// </summary>
        public void Update()
        {
            foreach (var a in Individuals)
                nextGeneration.Add(a);
            Mutation();
            Crossingover();
            nextGeneration.RemoveAll(x => x.Age >= 7);
            nextGeneration.Sort();
            for(int i = 0; i < length; ++i)
            {
                individuals[i] = nextGeneration.First();
                nextGeneration.Remove(nextGeneration.First());
            }
            nextGeneration.Clear();
        }


        //----------------------------------------------------
        //операторы
        //----------------------------------------------------
        /// <summary>
        /// Индексатор особей по индексу
        /// </summary>
        /// <param name="i">индекс </param>
        /// <returns></returns>
        public Individual this[int i]
        {
            get
            {
                if (i < length)
                    return individuals[i];
                    else throw new IndexOutOfRangeException();
            }
            set
            {
                if(i<length)
                    individuals[i] = value;
                    else throw new  IndexOutOfRangeException();
            }
        }


        //----------------------------------------------------
        //конструкторы
        //----------------------------------------------------
        /// <summary>
        /// генерирует случайное поколение длинны length
        /// </summary>
        public Generation()
        {
            individuals = new Individual[length];
            for (int i = 0; i < length; ++i)
                individuals[i] = new Individual();
        }
        public Generation(int _length)
        {
            length = _length;
            individuals = new Individual[length];
            for (int i = 0; i < length; ++i)
                individuals[i] = new Individual();
            Array.Sort(individuals);
        }
    }
}
