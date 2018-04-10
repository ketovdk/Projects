using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class Program
    {
        static private Random rand= new Random();
        static public Random Rand
        {
            get
            {
                return rand;
            }
        }


        static private IPrinter printer = new FilePrinter("Test18.txt");
        static private IReader reader = new ConsoleReader();

        static private int mutationConst = 10;
       
        static private int amountOfWorks;
        static public int AmountOfWorks
        {
            get
            {
                return amountOfWorks;
            }
        }


        static private int amountOfWorkers;
        static public int AmountOfWorkers
        {
            get
            {
                return amountOfWorkers;
            }
        }


        static private Work[] works;
        static public Work[] Works
        {
            get
            {
                return works;
            }
        }

        /// <summary>
        /// Выполняет алгоритм на поколении указанного размера и количества итераций
        /// </summary>
        /// <param name="size">Размер поколения</param>
        /// <param name="iterations">Количество итераций</param>
        /// <returns></returns>
        static Individual CalculateByIterations(int size, int iterations)
        {
            Individual answer;
            Generation generation = new Generation(size);
            generation.MutationConst = mutationConst;
            answer = generation[0];

            for (int i = 0; i < iterations; ++i)
            {
                generation.Update();
                if (answer.Strength < generation[0].Strength)
                    answer = generation[0];
            }

            return answer;
        }

        /// <summary>
        /// Выполняет алгоритм на протяжении указанного времени
        /// </summary>
        /// <param name="size">Количество особей в поколении</param>
        /// <param name="seconds">Длительность работы в секундах</param>
        /// <returns></returns>
        static Individual CalculateByTime(int size, int seconds)
        {
            Individual answer;
            var currentTime = DateTime.Now;
            Generation generation = new Generation(size);
            generation.MutationConst = mutationConst;
            answer = generation[0];
           while (true)
            {
                generation.Update();
                if (answer.Strength < generation[0].Strength)
                    answer = generation[0];
                var cur = DateTime.Now - currentTime;
                if (cur.Seconds+cur.Minutes*60+cur.Hours*3600 > seconds ) break;
            }

            return answer;
        }


        /// <summary>
        /// Несколько раз вызывает метод CalculateByITerations на разных показателях и выводит информацию о работе
        /// </summary>
        /// <param name="answer">Длительность оптимальной расстановки работ</param>
        static void CalculateData(int answer)
        {
            for(int i = 1; i <=10; ++i)
            {
                string output = 100 + " " + 1000 * i + " ";
                var currentTime = DateTime.Now;
                output += (CalculateByIterations(100, 1000 * i).Strength - answer) + " " + ((DateTime.Now - currentTime).Seconds+(DateTime.Now-currentTime).Minutes*60 + (DateTime.Now - currentTime).Hours * 3600);
                printer.WriteLine(output);
                Console.WriteLine("1");
            }
            for (int i = 1; i <= 10; ++i)
            {
                string output = 500 + " " + 1000 * i + " ";
                var currentTime = DateTime.Now;
                output += (CalculateByIterations(500, 1000 * i).Strength - answer) + " " + ((DateTime.Now - currentTime).Seconds + (DateTime.Now - currentTime).Minutes * 60 + (DateTime.Now - currentTime).Hours * 3600);
                printer.WriteLine(output);
                Console.WriteLine("2");
            }
            for (int i = 1; i <= 10; ++i)
            {
                string output = 1000 + " " + 1000 * i + " ";
                var currentTime = DateTime.Now;
                output += (CalculateByIterations(1000, 1000 * i).Strength - answer) + " " + ((DateTime.Now - currentTime).Seconds + (DateTime.Now - currentTime).Minutes * 60 + (DateTime.Now - currentTime).Hours * 3600);
                printer.WriteLine(output);
                Console.WriteLine("3");
            }
        }

        /// <summary>
        /// Получает перестановку в обычной форме
        /// </summary>
        /// <returns>массив, в котором заложена перестановка</returns>
        static private int[] GetNormalPermutation(int length, Permutation genes)
        {
            int[] returningAnswer = new int[length];
            List<int> permutation = new List<int>();
            for (int i = 0; i < length; ++i)
                permutation.Add(i);
            for (int i = 0; i < length; ++i)
            {
                returningAnswer[i] = permutation[genes[i]];
                permutation.Remove(returningAnswer[i]);
            }

            return returningAnswer;

        }



        //----------------------------------------------------
        // Адаптор для демонстрации комиссии
        //----------------------------------------------------

        /// <summary>
        /// получить число типа Int с проверкой корректности ввода
        /// </summary>
        /// <param name="question">Запрос, который показывается пользователю</param>
        /// <param name="mistake">Сообщение, выдаваемое в случае некорректного ввода</param>
        /// <returns>Введенное число</returns>
        static int getInteger(string question, string mistake)
        {
            Console.WriteLine(question);
            int returningAnswer=0;
            bool ok = false;
            while (!ok)
            {
                ok = Int32.TryParse(Console.ReadLine(), out returningAnswer);
                Console.Clear();
                if (!ok) Console.WriteLine(mistake);
            }
            return returningAnswer;
        }

        static void TimeMenu()
        {
            Console.Clear();
            int testNumber = getInteger("Введите номер теста", "Вы ввели некорректные данные, введите номер теста еще раз");
            int size = getInteger("Введите размер поколения", "Вы ввели некорретный размер поколения, введите данные еще раз");
            int time = getInteger("Введите время в секундах", "Вы ввели некорректное время, введите время еще раз");
            string inputFile = "Test" + testNumber + "Input.txt";
            string outputFile = "Test" + testNumber + "Output.txt";
            reader = new FileReader(inputFile);
            printer = new FilePrinter(outputFile);

            //Ввод количества работников
            amountOfWorkers = Int32.Parse(reader.ReadLine());


            //Ввод количества работ
            amountOfWorks = Int32.Parse(reader.ReadLine());


            //Ввод длительностей работ и инициализация массива работ
            works = new Work[amountOfWorks];
            string[] input = reader.ReadLine().Split();
            for (int i = 0; i < amountOfWorks; ++i)
                works[i] = new Work(Int32.Parse(input[i]));


            //ввод количества ребер(зависимостей работ) и добавление родителей для работ
            int numberOfEdges = Int32.Parse(reader.ReadLine());
            for (int i = 0; i < numberOfEdges; ++i)
            {
                input = reader.ReadLine().Split();
                works[Int32.Parse(input[1]) - 1].AddParent(works[Int32.Parse(input[0]) - 1]);
            }


            Individual.Length = amountOfWorks;
            Permutation.Length = amountOfWorks;

            printer.WriteLine(CalculateByTime(size, time).Strength.ToString());

            reader.Clear();
            printer.Clear();
            Console.Clear();
            PrintMenu();
        }
        static void IterationMenu()
        {
            Console.Clear();
            int testNumber = getInteger("Введите номер теста", "Вы ввели некорректные данные, введите номер теста еще раз");
            int size = getInteger("Введите размер поколения", "Вы ввели некорретный размер поколения, введите данные еще раз");
            int iterations = getInteger("Введите количество итераций", "Вы ввели некорректное количество итераций, введите данные еще раз");
            string inputFile = "Test" + testNumber + "Input.txt";
            string outputFile = "Test" + testNumber + "Output.txt";
            reader = new FileReader(inputFile);
            printer = new FilePrinter(outputFile);

            //Ввод количества работников
            amountOfWorkers = Int32.Parse(reader.ReadLine());


            //Ввод количества работ
            amountOfWorks = Int32.Parse(reader.ReadLine());


            //Ввод длительностей работ и инициализация массива работ
            works = new Work[amountOfWorks];
            string[] input = reader.ReadLine().Split();
            for (int i = 0; i < amountOfWorks; ++i)
                works[i] = new Work(Int32.Parse(input[i]));


            //ввод количества ребер(зависимостей работ) и добавление родителей для работ
            int numberOfEdges = Int32.Parse(reader.ReadLine());
            for (int i = 0; i < numberOfEdges; ++i)
            {
                input = reader.ReadLine().Split();
                works[Int32.Parse(input[1]) - 1].AddParent(works[Int32.Parse(input[0]) - 1]);
            }


            Individual.Length = amountOfWorks;
            Permutation.Length = amountOfWorks;

            printer.WriteLine(CalculateByIterations(size,iterations).Strength.ToString());

            reader.Clear();
            printer.Clear();
            Console.Clear();
            PrintMenu();
        }
        static void PrintMenu()
        {
            Console.WriteLine(@"Введите номер действия:
1. Подсчет по времени
2. Подсчет по количеству итераций
3. Закончить работу
");
            string input = Console.ReadLine();
            switch (input)
            {
                case "1": TimeMenu();
                    break;
                case "2": IterationMenu();
                    break;
                case "3":
                    break;
                default: Console.Clear();
                    PrintMenu();
                    break;
            }
        }
        static void Main(string[] args)
        {
            PrintMenu();
            
            /*
            rand = new Random();
            amountOfWorkers = 5;
            amountOfWorks = 150;
            int edgeChance = 50;
            Individual.Length = amountOfWorks;
            Permutation.Length = amountOfWorks;
            works = new Work[amountOfWorks];
            for (int i = 0; i < amountOfWorks; ++i)
                works[i] = new Work(rand.Next(1, 150));
            var perm = GetNormalPermutation(amountOfWorks, new Permutation());
            for (int i = 0; i < amountOfWorks; ++i)
                for (int j = i + 1; j < amountOfWorks; ++j)
                    if (rand.Next(1, edgeChance) == 1)
                        works[perm[j]].AddParent(works[perm[i]]);
            
           
            CalculateData(0);
            printer.Clear();
            reader.Clear();

            Console.WriteLine("End");
            */
        }
    }
}
