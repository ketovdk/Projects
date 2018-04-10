using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    /// <summary>
    /// особь
    /// </summary>
    class Individual : IComparable
    {
        //----------------------------------------------------
        //Поля
        //----------------------------------------------------

        //количество генов 
        static private int length = 0;
        static public int Length
        {
            get
            {
                return length;
            }
            set
            {
                if (value > 0) length = value;
            }
        }

        // гены
        private Permutation genes;
        public Permutation Genes
        {
            get { return genes; }
          
        }

        //возраст особи(увеличивается, если особь не производит более слиьных особей, в случае создания более сильной особи обнуляется
        private int age = 0;
        public int Age
        {
            get
            {
                return age;
            }
            set
            {
                if (value >= 0) age = value;
                else throw new Exception("Возраст не может быть отрицательным");
            }
        }

        //сила или фитнесс-функция особи. Чем меньше, тем лучше
        private int strength;
        public int Strength
        {
            get
            {
                return strength;
            }
         
        }



        //----------------------------------------------------
        //Методы
        //----------------------------------------------------

        /// <summary>
        /// возвращает мутацию текущей особи
        /// </summary>
        /// <returns> мутация текущей особи </returns>
        public Individual Mutate()
        {
           
            int geneNumber = Program.Rand.Next(0, length );
            int[] nums = new int[length];
            for (int i = 0; i < length; ++i)
                nums[i] = genes[i];
            nums[geneNumber] = Program.Rand.Next(1, length - geneNumber+1)-1;
            return new Individual(new Permutation(nums));
        }
        
        /// <summary>
        /// кроссинговер двух особей
        /// </summary>
        /// <param name="firstParent">первый родитель</param>
        /// <param name="secondParent">второй родитель</param>
        /// <param name="firstSon">первый результат кроссинговера</param>
        /// <param name="secondSon">второй результат кроссинговера</param>
        static public void Crossingover(Individual firstParent, Individual secondParent, out Individual firstSon, out Individual secondSon)
        {
            int[] firstSonNums = new int[Permutation.Length];
            int[] secondSonNums = new int[Permutation.Length];
            for(int i = 0; i < Permutation.Length/2; ++i)
            {
                firstSonNums[i] = firstParent.Genes[i];
                secondSonNums[i] = secondParent.Genes[i];
            }
            for(int i = Permutation.Length/2; i < length; ++i)
            {
                firstSonNums[i] = secondParent.Genes[i];
                secondSonNums[i] = firstParent.Genes[i];
            }
            firstSon = new Individual(new Permutation(firstSonNums));
            secondSon = new Individual(new Permutation(secondSonNums));
            if (firstParent.Strength >= firstSon.Strength && firstParent.Strength >= secondSon.Strength)
                firstParent.Age++;
            else
                firstParent.Age = 0;
            if (secondParent.Strength >= firstSon.Strength && secondParent.Strength >= secondSon.Strength)
                secondParent.Age++;
            else
                secondParent.Age = 0;
        }

        /// <summary>
        /// вычисляет фитнесс-функцию или силу текущей особи
        /// </summary>
        public void CalculateStrngth()
        {
            var permutation = GetNormalPermutation();
            for (int i = 0; i < permutation.Length; ++i)
                Program.Works[permutation[i]].Priority = i;
            for (int i = 0; i < length; ++i)
                Program.Works[i].InList = false;
            var priorityList = new List<Work>();
            foreach (var a in permutation)
                if (!Program.Works[a].InList)
                    AddInPriorityList(Program.Works[a], priorityList);
            for (int i = 0; i < length; ++i)
                Program.Works[i].WorkEndTime = 0;
            int[] workers = new int[Program.AmountOfWorkers];
            for (int i = 0; i < workers.Length; ++i)
                workers[i] = 0;
            foreach(var a in priorityList)
            {
                int max = 0;
                foreach (var from in a.Parents)
                    if (from.WorkEndTime > max) max = from.WorkEndTime;
                int min = Int32.MaxValue;
                int index = -1;
                bool foundLower = false;
                for(int i = 0; i < workers.Length; ++i)
                {
                    if(workers[i]<=max)
                    {
                        foundLower = true;
                    }
                }
                if(foundLower)
                {
                    index = 0;
                    int best = 0;
                    for(int i =0; i<workers.Length; ++i)
                    {
                        if(workers[i]<=max&&workers[i]>=best)
                        {
                            best = workers[i]; index = i;
                        }
                    }
                    workers[index] = max + a.WorkLength;
                    a.WorkEndTime = workers[index];
                } else
                {
                    index = 0;
                    for (int i = 0; i < workers.Length; ++i)
                        if (workers[i] <= min)
                        {
                            min = workers[i]; index = i;
                        }
                    workers[index] += a.WorkLength;
                    a.WorkEndTime = workers[index];
                }
            }
            strength = workers.Max();

        }

        public void AddInPriorityList(Work from, List<Work>priorityList)
        {

            var previouses = new List<Work>(from.Parents);
            previouses.RemoveAll(x => x.InList);
            //previouses = (from c in previouses where !c.InList select c).ToList();
            ListMerdgeSorter<Work>.MerchSort(previouses);
            foreach (var a in previouses)
                AddInPriorityList(a,  priorityList);
            from.InList = true;
            priorityList.Add(from);
        }

        /// <summary>
        /// Получает перестановку в обычной форме
        /// </summary>
        /// <returns>массив, в котором заложена перестановка</returns>
        public int[] GetNormalPermutation()
        {
            int[] returningAnswer = new int[length];
            List<int> permutation= new List<int>();
            for (int i = 0; i < length; ++i)
                permutation.Add(i);
            for(int i = 0; i < length; ++i)
            {
                returningAnswer[i] = permutation[genes[i]];
                permutation.Remove(returningAnswer[i]);
            }

            return returningAnswer;

        }
        /// <summary>
        /// компаратор по силе
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public int CompareTo(object obj)
        {
            return strength.CompareTo((obj as Individual).Strength);
        }

        //----------------------------------------------------
        //операторы
        //----------------------------------------------------

        /// <summary>
        /// индексатор, возвращающий ген с указанным индексом
        /// </summary>
        /// <param name="i"></param>
        /// <returns></returns>
        public int this[int i]
        {
            get
            {
                if (i < length)
                    return genes[i];
                else throw new IndexOutOfRangeException();
            }
            set
            {
                if (i < length)
                    genes[i] = value;
                else throw new IndexOutOfRangeException();
            }
        }

        //----------------------------------------------------
        //Конструкторы
        //----------------------------------------------------

        /// <summary>
        /// случайная особь
        /// </summary>
        public Individual()
        {
            genes = new Permutation();
            CalculateStrngth();
        }
        
        /// <summary>
        /// создание особи на основе перестановки
        /// </summary>
        /// <param name="_genes">перестановка, на основе которой создается особь</param>
        public Individual(Permutation _genes)
        {
            genes = _genes;
            CalculateStrngth();
        }
    }
}
