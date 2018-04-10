using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class Work:IComparable
    {
        //----------------------------------------------------
        //Поля
        //----------------------------------------------------
        
            
        //приоритет работы
        private int priority=0;
        public int Priority
        {
            get
            {
                return priority;
            }
            set
            {
                if (value < 0)
                {
                    throw new Exception("Приоритет не может быть отрицательным");
                }
                else
                    priority = value;
            }
        }


        //длинна работы
        private int workLength;
        public int WorkLength
        {
            get
            {
                return workLength;
            }
            set
            {
                if (value < 0)
                    throw new Exception("Длинна работы не может быть отрицательной");
                else
                    workLength = value;
            }
        }


        //окончание работы
        private int workEndTime;
        public int WorkEndTime
        {
            get
            {
                return workEndTime;
            }
            set
            {
                if (value < 0)
                    throw new Exception("Окончание работы не может быть отрицательным");
                else
                    workEndTime = value;
            }
        }


        //флаг, что работа находится в списке приоритетов 
        private Boolean inList;
        public Boolean InList
        {
            get
            {
                return inList;
            }
            set
            {
                inList = value;
            }
        }


        // работы, которые должны быть выполнены до данной
        private List<Work> parents = new List<Work>();
        public List<Work> Parents
        {
            get
            {
                return parents;
            }
        }

        //----------------------------------------------------
        //Методы
        //----------------------------------------------------

        /// <summary>
        /// Добавляет работу, от которой зависит данная в список приоритетов
        /// </summary>
        /// <param name="parent">добавляемая работа</param>
        public void AddParent(Work parent)
        {
            parents.Add(parent);
        }

        public int CompareTo(object obj)
        {
            return priority.CompareTo((obj as Work).Priority);
        }


        //----------------------------------------------------
        //Конструкторы
        //----------------------------------------------------
        public Work(int _workLength)
        {
            workLength = _workLength;
        }

    }
}
