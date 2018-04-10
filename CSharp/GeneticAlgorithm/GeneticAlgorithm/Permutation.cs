using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    /// <summary>
    /// перестановка в общей форме
    /// </summary>
    class Permutation
    {

      
        //----------------------------------------------------
        //Поля
        //----------------------------------------------------

        //длинна перестановки
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
                    else throw new Exception("Размер не может быть отрицательным");
            }
        }

        //контейнер, для значений перестановки
        private int[] nums;
        public int[] Nums
        {
            get { return nums; }
        }


        //----------------------------------------------------
        //Операторы
        //----------------------------------------------------

        /// <summary>
        /// индексатор, возвращающий элемент перестановки по индексу
        /// </summary>
        /// <param name="i">индекс</param>
        /// <returns></returns>
        public int this[int i]
        {
            get
            {
                if (i < length)
                    return nums[i];
                else throw new IndexOutOfRangeException();
            }
            set
            {
                if (i < length)
                    nums[i] = value;
                else throw new IndexOutOfRangeException();
            }
        }

        //----------------------------------------------------
        //Конструкторы
        //----------------------------------------------------

        /// <summary>
        /// генерация случайной перестановки
        /// </summary>
        public Permutation()
        {
   
           
            nums = new int[length];
            for (int i = 0; i < length; ++i)
                nums[i] = Program.Rand.Next(1, length - i+1)-1;
        }

        /// <summary>
        /// перестановка на основе массива элементов перестановки
        /// </summary>
        /// <param name="_nums"></param>
        public Permutation(int[] _nums)
        {
            if (_nums.Length == length)
                nums = _nums;
            else
                throw new Exception("Не подходящая длинна перестановки");
        }
    }
}
