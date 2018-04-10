using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class ListMerdgeSorter<T> where T : IComparable
    {
        /// <summary>
        /// сортировка слиянием
        /// </summary>
        /// <param name="list">сортируемый список</param>
        static public void MerchSort(List<T> list)
        {
            var listA = new List<T>();
            var listB = new List<T>();
            int listLength = list.Count;
            if (listLength <= 1) return;
            for (int i = 0; i < listLength / 2; ++i)
            {
                listA.Add(list.First());
                list.Remove(list.First());
            }
            for (int i = listLength / 2; i < listLength; ++i)
            {
                listB.Add(list.First());
                list.Remove(list.First());
            }
            MerchSort(listA);
            MerchSort(listB);
            while (listA.Count > 0 && listB.Count > 0)
            {
                if (listA.First().CompareTo(listB.First()) < 0)
                {
                    list.Add(listA.First()); listA.Remove(listA.First());
                }
                else
                {

                    list.Add(listB.First()); listB.Remove(listB.First());
                }
            }
            foreach (var a in listA) list.Add(a);
            foreach (var b in listB) list.Add(b);

        }
    }
}
